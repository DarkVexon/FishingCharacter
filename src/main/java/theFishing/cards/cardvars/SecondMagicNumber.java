package theFishing.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;

public class SecondMagicNumber extends DynamicVariable {

    @Override
    public String key() {
        return makeID("m2");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).isSecondMagicModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).secondMagic;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractFishingCard) {
            ((AbstractFishingCard) card).isSecondMagicModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).baseSecondMagic;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).upgradedSecondMagic;
        }
        return false;
    }
}