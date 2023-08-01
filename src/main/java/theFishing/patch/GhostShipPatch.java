package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import theFishing.cards.StartOfTurnInExhaustCard;

public class GhostShipPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnCards"
    )
    public static class TriggerGhostShipsFromExhaustPatch {
        public static void Postfix(AbstractPlayer __instance) {
            for (AbstractCard q : __instance.exhaustPile.group) {
                if (q instanceof StartOfTurnInExhaustCard) {
                    ((StartOfTurnInExhaustCard) q).atTurnStartInExhaust();
                }
            }
        }
    }
}
