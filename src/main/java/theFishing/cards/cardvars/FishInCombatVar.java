package theFishing.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.cards.fish.AbstractFishCard;

public class FishInCombatVar extends DynamicVariable {
    @Override
    public String key() {
        return FishingMod.makeID("fshhd");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return value(card) != 0;
    }

    @Override
    public int value(AbstractCard card) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group)
            if (c instanceof AbstractFishCard)
                count++;
        for (AbstractCard c : AbstractDungeon.player.hand.group)
            if (c instanceof AbstractFishCard)
                count++;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof AbstractFishCard) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}
