package theFishing.patch.endsoftheearth;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "updateInput"
)
public class HoverEnds {
    private static final float HOVERED_X_POSITION = 1750 * Settings.scale;
    private static final float HOVERED_Y_POSITION = 470 * Settings.scale;

    @SpireInsertPatch(
            locator = FirstLocator.class
    )
    public static void GetHoverOtherHand(AbstractPlayer __instance) {
        if (__instance.hoveredCard == null) {
            for (AbstractCard top : TrackEnds.validEnds) {
                top.hb.update();
                if (top.hb.hovered && top.current_x > (1700 * Settings.scale)) {
                    for (CardQueueItem q : AbstractDungeon.actionManager.cardQueue) {
                        if (q.card == top)
                            return;
                    }
                    __instance.hoveredCard = top;
                }
            }
        }
    }

    @SpireInsertPatch(
            locator = AngleLocator.class
    )
    public static void reposition(AbstractPlayer __instance) {
        if (TrackEnds.validEnds.contains(__instance.hoveredCard)) {
            __instance.hoveredCard.current_x = HOVERED_X_POSITION;
            __instance.hoveredCard.target_x = HOVERED_X_POSITION;
            __instance.hoveredCard.current_y = HOVERED_Y_POSITION - (TrackEnds.CARD_SPACING * TrackEnds.validEnds.indexOf(__instance.hoveredCard));
            __instance.hoveredCard.target_y = HOVERED_Y_POSITION - (TrackEnds.CARD_SPACING * TrackEnds.validEnds.indexOf(__instance.hoveredCard));
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "hoverCardPush"
    )
    public static class NoPush {
        @SpirePrefixPatch
        public static SpireReturn no(CardGroup __instance, AbstractCard c) {
            if (TrackEnds.validEnds.contains(c)) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    /*@SpireInsertPatch(
            locator = Locator.class
    )
    public static void TestHoverOtherHand(AbstractPlayer __instance)
    {
        if (__instance.hasRelic(dunno.ID))
        {
            if (__instance.toHover == null)
            {
                AbstractCard top = RenderAndClickableTopCard.Fields.currentCard.get(__instance.drawPile);
                if (top != null)
                {
                    if (top == __instance.hoveredCard) {
                        __instance.toHover = top;
                    }
            }
        }
    }*/

    private static class FirstLocator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            ArrayList<Matcher> requiredMatches = new ArrayList<>();
            requiredMatches.add(new Matcher.MethodCallMatcher(CardGroup.class, "getHoveredCard"));
            requiredMatches.add(new Matcher.FieldAccessMatcher(AbstractPlayer.class, "hoveredCard"));

            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "hoveredCard");

            return LineFinder.findInOrder(ctMethodToPatch, requiredMatches, finalMatcher);
        }
    }

    private static class AngleLocator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "setAngle");

            ArrayList<Matcher> requiredMatches = new ArrayList<>();
            requiredMatches.add(finalMatcher);

            return LineFinder.findInOrder(ctMethodToPatch, requiredMatches, finalMatcher);
        }
    }
}