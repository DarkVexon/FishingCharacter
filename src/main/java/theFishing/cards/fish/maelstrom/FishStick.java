package theFishing.cards.fish.maelstrom;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class FishStick extends AbstractFishCard {
    public final static String ID = makeID("FishStick");
    // intellij stuff skill, self, , , , , 1, 1

    public FishStick() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p, p, secondMagic));
        applyToSelf(new StrengthPower(p, magicNumber));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}