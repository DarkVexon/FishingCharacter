package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theFishing.actions.ScryCallbackAction;

import java.util.ArrayList;

@SpirePatch(clz = ScryAction.class, method = "update")
public class ScryCallbackPatch {

    @SpireInsertPatch(locator = LocatorClear.class)
    public static void InsertCallback(ScryAction __instance) {
        if (__instance instanceof ScryCallbackAction) {
            ((ScryCallbackAction) __instance).callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
        }
    }

    private static class LocatorClear extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "clear");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
