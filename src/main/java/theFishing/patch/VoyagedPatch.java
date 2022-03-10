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
    public static class AbstractPlayerApplyStartOfTurnPostDrawRelicsPatch {
        public static void Prefix(AbstractPlayer __instance) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    PreDrawPatch.DRAWN_DURING_TURN = true;
                    FishingMod.voyagedCards.clear();
                }
            });
        }
    }
}
