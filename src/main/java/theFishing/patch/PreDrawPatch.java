package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.Bobber;
import theFishing.util.Wiz;

public class PreDrawPatch {
    public static boolean DRAWN_DURING_TURN = false;

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnRelics"
    )
    public static class AbstractPlayerApplyStartOfTurnRelicsPatch {
        public static void Prefix(AbstractPlayer __instance) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    DRAWN_DURING_TURN = false;
                    isDone = true;
                }
            });
        }
    }
}
