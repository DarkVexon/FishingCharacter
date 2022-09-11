package theFishing.patch.endsoftheearth;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
import javassist.CtBehavior;
import theFishing.cards.EndsOfTheEarth;

import java.util.ArrayList;

public class TrackEnds {
    private static final float RENDER_X = 1800 * Settings.scale;
    private static final float RENDER_Y = 400 * Settings.scale;
    public static final float CARD_SPACING = 266 * Settings.scale;

    public static ArrayList<AbstractCard> validEnds = new ArrayList<>();

    @SpirePatch(
            clz = DiscardPilePanel.class,
            method = "updatePositions"
    )
    public static class UpdatePatch {
        public static void Postfix(DiscardPilePanel __instance) {
            if (!AbstractDungeon.isScreenUp && AbstractDungeon.actionManager.actions.isEmpty() && AbstractDungeon.actionManager.currentAction == null) {
                validEnds.removeIf(q -> !AbstractDungeon.player.discardPile.group.contains(q));

                for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
                    if (q.cardID.equals(EndsOfTheEarth.ID)) {
                        if (!validEnds.contains(q)) {
                            validEnds.add(q);
                            glowCheck(q);
                            setPosition(q);
                        }
                        if (!q.equals(AbstractDungeon.player.hoveredCard)) {
                            setPosition(q);
                            q.lighten(true);
                        }
                        q.update();
                    }
                }
            } else {
                for (AbstractCard q : validEnds) {
                    if (AbstractDungeon.player.discardPile.contains(q)) {
                        partialReset(q);
                        q.shrink();
                        AbstractDungeon.getCurrRoom().souls.discard(q, true);
                    }
                }
                validEnds.clear();
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "applyPowers"
    )
    public static class ApplyPowers {
        @SpirePostfixPatch
        public static void apply(CardGroup __instance) {
            for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
                if (validEnds.contains(q)) {
                    q.applyPowers();
                }
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "glowCheck"
    )
    public static class GlowCheck {
        @SpirePostfixPatch
        public static void apply(CardGroup __instance) {
            for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
                if (validEnds.contains(q)) {
                    glowCheck(q);
                    q.triggerOnGlowCheck();
                }
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "updateHoverLogic"
    )
    public static class UpdateHoverLogic {
        @SpirePostfixPatch
        public static void update(CardGroup __instance) {
            for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
                if (validEnds.contains(q) && q.current_x > (1700 * Settings.scale)) {
                    q.updateHoverLogic();
                }
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "refreshHandLayout"
    )
    public static class RefreshLayout {
        @SpireInsertPatch(
                locator = HoverLocator.class
        )
        public static void onRefreshLayout(CardGroup __instance) {
            for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
                if (validEnds.contains(q)) {
                    setPosition(q);
                }
            }
        }

        private static class HoverLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "hoveredCard");

                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "canUseAnyCard"
    )
    public static class MaybeYouCan {
        @SpirePostfixPatch
        public static boolean maybe(boolean __result, CardGroup __instance) {
            return __result || (AbstractDungeon.player.discardPile.group.stream().anyMatch(q -> q.cardID.equals(EndsOfTheEarth.ID) && q.hasEnoughEnergy()));
        }
    }

    public static void glowCheck(AbstractCard c) {
        if (c != null) {
            if (c.canUse(AbstractDungeon.player, null) && !AbstractDungeon.isScreenUp) {
                c.beginGlowing();
            } else {
                c.stopGlowing();
            }
        }
    }

    public static void partialReset(AbstractCard c) {
        c.block = c.baseBlock;
        c.isBlockModified = false;
        c.damage = c.baseDamage;
        c.isDamageModified = false;
        c.magicNumber = c.baseMagicNumber;
        c.isMagicNumberModified = false;

        c.stopGlowing();
    }

    public static void setPosition(AbstractCard c) {
        c.targetDrawScale = 0.5f;
        c.targetAngle = 0;
        c.target_x = RENDER_X;
        c.target_y = RENDER_Y + (CARD_SPACING * validEnds.indexOf(c));
    }
}
