package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.fish.AbstractFishCard;

public class ConsumeMod extends AbstractCardModifier implements AlternateCardCostModifier {

    @Override
    public int spendAlternateCost(AbstractCard abstractCard, int i) {
        int needed = i;
        int x = 0;
        while (needed > 0 && x < AbstractDungeon.player.hand.size()) {
            AbstractCard q = AbstractDungeon.player.hand.group.get(i);
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
    public boolean isInherent(AbstractCard card) {
        return true;
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
    public int getAlternateResource(AbstractCard abstractCard) {
        return (int) AbstractDungeon.player.hand.group.stream().filter(c -> c instanceof AbstractFishCard).count();
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ConsumeMod();
    }
}
