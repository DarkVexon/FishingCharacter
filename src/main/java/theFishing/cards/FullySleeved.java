package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.FullySleevedPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class FullySleeved extends AbstractFishingCard {
    public final static String ID = makeID("FullySleeved");
    // intellij stuff power, self, uncommon, , , 6, 2, , 

    public FullySleeved() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FullySleevedPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}