package theFishing.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;

public class ThirdMagicNumber extends DynamicVariable {

    @Override
    public String key() {
        return makeID("m3");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).isThirdMagicModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).thirdMagic;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractFishingCard) {
            ((AbstractFishingCard) card).isThirdMagicModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).baseThirdMagic;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractFishingCard) {
            return ((AbstractFishingCard) card).upgradedThirdMagic;
        }
        return false;
    }
}