package theFishing.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;

public class SecondDamage extends DynamicVariable {

    @Override
    public String key() {
        return makeID("sd");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).isSecondDamageModified;
        }
        return false;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractFishingCard) {
            ((AbstractFishingCard) card).isSecondDamageModified = v;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).secondDamage;
        }
        return -1;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).baseSecondDamage;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).upgradedSecondDamage;
        }
        return false;
    }
}