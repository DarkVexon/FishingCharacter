package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class ScalingIsWin extends AbstractBoxTopper {
    public final static String ID = makeID("ScalingIsWin");
    // intellij stuff power, self, special, , , , , 1, 1

    public ScalingIsWin() {
        super(ID, 1, CardType.POWER, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DemonFormPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}