package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;

public class VoyagedPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnPostDrawRelics"
    )
    public static class AbstractPlayerApplyStartOfTurnRelicsPatch {
        public static void Prefix(AbstractPlayer __instance) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    FishingMod.determineNonVoyagedCards();
                }
            });
        }
    }
}
