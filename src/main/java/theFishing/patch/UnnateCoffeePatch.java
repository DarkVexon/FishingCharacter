package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import java.util.ArrayList;

public class UnnateCoffeePatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class UnnateField {
        public static SpireField<Boolean> unnate = new SpireField<>(() -> false);
    }

    @SpirePatch(clz = CardGroup.class, method = "initializeDeck")
    public static class UnnateDeckInitializePatch {
        @SpireInsertPatch(
                rloc = 6,
                localvars = {"copy"}
        )
        public static void initializeDeckPatch(CardGroup combatDeck, @ByRef CardGroup[] copy) {
            ArrayList<AbstractCard> placeOnBot = new ArrayList();
            for (AbstractCard q : copy[0].group) {
                if (UnnateField.unnate.get(q)) {
                    placeOnBot.add(q);
                }
            }
            if (placeOnBot.size() > 0) {
                for (AbstractCard abstractCard : placeOnBot) {
                    copy[0].removeCard(abstractCard);
                    copy[0].addToBottom(abstractCard);
                }
            }
        }
    }
}
