package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.PyramidClimbPower;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class PyramidClimber extends AbstractFishingCard {
    public final static String ID = makeID(PyramidClimber.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 8, 3, , 

    public PyramidClimber() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 4;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new PyramidClimbPower(magicNumber));
    }

    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}