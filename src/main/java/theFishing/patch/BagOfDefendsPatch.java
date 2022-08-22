package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.OnRemoveCardFromDeckCard;

public class BagOfDefendsPatch {
    @SpirePatch(
            clz = CardGroup.class,
            method = "removeCard",
            paramtypez = {
                    AbstractCard.class
            }
    )
    public static class OnRemoveDefendDoAThing {
        public static void Postfix(CardGroup __instance, AbstractCard toRemove) {
            if (__instance.type == CardGroup.CardGroupType.MASTER_DECK && toRemove.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
                for (AbstractCard q : AbstractDungeon.player.masterDeck.group) {
                    if (q instanceof OnRemoveCardFromDeckCard) {
                        ((OnRemoveCardFromDeckCard) q).onRemoveCardFromDeck(toRemove);
                    }
                }
            }
        }
    }
}
