package theFishing.patch.questhooks;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import theFishing.quest.QuestHelper;

@SpirePatch(
        clz = CardGroup.class,
        method = "triggerOnOtherCardPlayed"
)
public class OnCardUsePatch {
    public static void Prefix(CardGroup __instance, AbstractCard abstractCard) {
        QuestHelper.onCardPlayed(abstractCard);
    }
}