package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SadisticPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class GolemGuardian extends AbstractFishingCard {
    public final static String ID = makeID(GolemGuardian.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , 4, 2, 4, 2

    public GolemGuardian() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 5;
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new SadisticPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(2);
    }
}