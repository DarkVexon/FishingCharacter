package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.CollectorPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Collector extends AbstractFishingCard {
    public final static String ID = makeID("Collector");
    // intellij stuff power, self, uncommon, , , , , , 

    public Collector() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CollectorPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(3);
    }
}