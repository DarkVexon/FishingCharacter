package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import theFishing.powers.AllOutPower;

// This patch is needed for Mayhem Form to replicate what Unceasing Top does to prevent it from triggering when
// cards like burns automatically play and exit the hand while the turn is ending
@SpirePatch2(clz = GameActionManager.class, method = "getNextAction", paramtypez = {})
public class SpecialDisablePatchForAllOut {
    @SpireInsertPatch(locator = Locator.class)
    public static void disableMayhemFormWhileTurnEnding() {
        if (AbstractDungeon.player.hasPower(AllOutPower.ID)) {
            ((AllOutPower) AbstractDungeon.player.getPower(AllOutPower.ID)).disableUntilTurnEnds();
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "getRelic");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}