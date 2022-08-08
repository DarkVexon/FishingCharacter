package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.VictoryLapPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class VictoryLap extends AbstractBoxTopper {
    public final static String ID = makeID("VictoryLap");
    // intellij stuff power, self, , , , , 12, 4

    public VictoryLap() {
        super(ID, 1, CardType.POWER, CardTarget.SELF);
        baseMagicNumber = magicNumber = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VictoryLapPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}