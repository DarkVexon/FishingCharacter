package theFishing.patch.endsoftheearth;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
import javassist.CtBehavior;
import theFishing.cards.EndsOfTheEarth;

public class RenderEnds {
    //patch refresh hand layout to also refresh position of top card

    @SpirePatch(
            clz = DiscardPilePanel.class,
            method = "render"
    )
    public static class Render {
        public static void Postfix(DiscardPilePanel __instance, SpriteBatch sb) {
            if (!AbstractDungeon.isScreenUp) {
                for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
                    if (q.cardID.equals(EndsOfTheEarth.ID)) {
                        if (!q.equals(AbstractDungeon.player.hoveredCard)) {
                            q.render(sb);
                        }
                    }
                }
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderEnergy"
    )
    public static class RenderAltColor {
        private static final Color RED = new Color(1.0f, 0.3f, 0.3f, 1.0f);

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"costColor"}
        )
        public static void modifyColor(AbstractCard __instance, SpriteBatch sb, @ByRef Color[] costColor) {
            if (AbstractDungeon.player != null) {
                if (TrackEnds.validEnds.contains(__instance))
                    if (!__instance.hasEnoughEnergy()) {
                        costColor[0] = RED;
                    }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "transparency");

                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}