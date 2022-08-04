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

import static theFishing.patch.endsoftheearth.TrackEnds.Fields.currentCard;

public class TrackEnds {
    private static final float RENDER_X = 120 * Settings.scale;
    private static final float RENDER_Y = 400 * Settings.scale;

    @SpirePatch(
            clz = CardGroup.class,
            method = SpirePatch.CLASS
    )
    public static class Fields {
        public static SpireField<ArrayList<AbstractCard>> currentCard = new SpireField<>(() -> new ArrayList<>());
    }

    @SpirePatch(
            clz = DiscardPilePanel.class,
            method = "updatePositions"
    )
    public static class Update {
        @SpirePostfixPatch
        public static void doTheUpdateThing(DiscardPilePanel __instance) {
            if (!AbstractDungeon.isScreenUp) {
                if (!AbstractDungeon.player.discardPile.isEmpty()) {
                    ArrayList<AbstractCard> found = new ArrayList<>();
                    for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
                        if (q.cardID.equals(EndsOfTheEarth.ID)) {
                            found.add(q);
                        }
                    }
                    for (AbstractCard top : found) {
                        if (!currentCard.get(AbstractDungeon.player.discardPile).contains(top)) {
                            currentCard.get(AbstractDungeon.player.discardPile).add(top);

                            glowCheck(top);
                            setPosition(top);
                        }
                        top.update();
                    }
                } else {
                    currentCard.get(AbstractDungeon.player.discardPile).clear();
                }
            } else {
                for (AbstractCard q : currentCard.get(AbstractDungeon.player.discardPile)) {
                    partialReset(q);
                    q.shrink();
                    AbstractDungeon.getCurrRoom().souls.discard(q, true);
                }

                currentCard.get(AbstractDungeon.player.discardPile).clear();
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
            for (AbstractCard q : currentCard.get(AbstractDungeon.player.discardPile)) {
                q.applyPowers();
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
            for (AbstractCard q : currentCard.get(AbstractDungeon.player.discardPile)) {
                glowCheck(q);
                q.triggerOnGlowCheck();
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
            for (AbstractCard q : currentCard.get(AbstractDungeon.player.discardPile)) {
                if (q != null)
                    q.updateHoverLogic();
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
            for (AbstractCard q : currentCard.get(AbstractDungeon.player.discardPile)) {
                if (q != null)
                    setPosition(q);
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
            return __result || (!currentCard.get(AbstractDungeon.player.discardPile).isEmpty() && AbstractDungeon.player.energy.energy > 0);
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
        c.target_y = RENDER_Y;
    }
}
