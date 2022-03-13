package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class HolyGrail extends AbstractTreasureCard {
    public final static String ID = makeID("HolyGrail");
    // intellij stuff skill, self, , , , , 3, 1

    public HolyGrail() {
        super(ID, 1, CardType.POWER, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BerserkPower(p, 2));
        applyToSelf(new DrawPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}