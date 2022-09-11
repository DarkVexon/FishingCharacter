package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.actions.EnableDrawnDuringTurnAction;

public class VoyagedPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnPostDrawRelics"
    )
    public static class AbstractPlayerApplyStartOfTurnPostDrawRelicsPatch {
        public static void Prefix(AbstractPlayer __instance) {
            AbstractDungeon.actionManager.addToBottom(new EnableDrawnDuringTurnAction());
        }
    }
}
