package theFishing.patch.questhooks;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import theFishing.quest.QuestHelper;

public class OnExhaustPatch {

    @SpirePatch(
            clz = CardGroup.class,
            method = "moveToExhaustPile"
    )
    public static class QuestDetectExhaustPatch {
        public static void Prefix(CardGroup __instance, AbstractCard c) {
            QuestHelper.onExhaust(c);
        }
    }
}
