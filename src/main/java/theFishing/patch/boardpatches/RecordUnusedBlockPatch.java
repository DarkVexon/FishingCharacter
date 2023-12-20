package theFishing.patch.boardpatches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theFishing.FishingMod;

public class RecordUnusedBlockPatch {
    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class SetupPowerValue {

        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(GameActionManager __instance) {
            if (FishingMod.activeBoard != null) {
                FishingMod.activeBoard.onUnusedBlock(AbstractDungeon.player.currentBlock);
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasPower");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
