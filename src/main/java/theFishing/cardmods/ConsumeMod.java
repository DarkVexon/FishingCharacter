package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.fish.AbstractFishCard;

import java.util.ArrayList;

public class ConsumeMod extends AbstractCardModifier implements AlternateCardCostModifier {

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public int getAlternateResource(AbstractCard card) {
        ArrayList<AbstractCard> cardsList = new ArrayList<>();
        for (AbstractCard q : AbstractDungeon.player.hand.group) {
            if (q instanceof AbstractFishCard) {
                cardsList.add(q);
            }
        }
        return cardsList.size();
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        int needed = costToSpend;
        int x = 0;
        while (needed > 0 && x < AbstractDungeon.player.hand.size()) {
            AbstractCard q = AbstractDungeon.player.hand.group.get(x);
            if (q instanceof AbstractFishCard) {
                AbstractDungeon.player.hand.moveToExhaustPile(q);
                q.exhaustOnUseOnce = false;
                q.freeToPlayOnce = false;
                needed--;
            } else x++;
        }
        return needed;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ConsumeMod();
    }
}
