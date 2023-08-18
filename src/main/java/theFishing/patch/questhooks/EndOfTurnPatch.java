package theFishing.patch.questhooks;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import theFishing.FishingMod;
import theFishing.quest.QuestHelper;

@SpirePatch(
        clz = GameActionManager.class,
        method = "callEndOfTurnActions"
)
public class EndOfTurnPatch {
    public static void Postfix(GameActionManager __instance) {
        QuestHelper.atEndOfTurn();
        if (FishingMod.activeBoard != null && FishingMod.activeBoard.shouldBeActive()) {
            FishingMod.activeBoard.atEndOfTurn();
        }
    }
}