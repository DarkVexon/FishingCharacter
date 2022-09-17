package theFishing.patch.endsoftheearth;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import javassist.CtBehavior;
import theFishing.cards.EndsOfTheEarth;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "clickAndDragCards"
)
public class SpecialEndsTargeting {
    @SpireInsertPatch(
            locator = Locator.class
    )

    public static SpireReturn<Boolean> specialEndsLogic(AbstractPlayer __instance) {
        if (__instance.hoveredCard.cardID.equals(EndsOfTheEarth.ID) && TrackEnds.validEnds.contains(__instance.hoveredCard)) {
            __instance.inSingleTargetMode = true;
            ReflectionHacks.setPrivate(__instance, AbstractPlayer.class, "arrowX", InputHelper.mX);
            ReflectionHacks.setPrivate(__instance, AbstractPlayer.class, "arrowY", InputHelper.mY);
            GameCursor.hidden = true;
            __instance.hoveredCard.untip();
            __instance.hand.refreshHandLayout();
            __instance.isDraggingCard = false;
            return SpireReturn.Return(true);
        }
        return SpireReturn.Continue();
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "inSingleTargetMode");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
