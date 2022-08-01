package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theFishing.cards.AbstractFishingCard;
import theFishing.powers.TakeItEasyPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class FastingB extends AbstractFishingCard {
    public final static String ID = makeID("FastingB");
    // intellij stuff power, self, uncommon, , , , , 4, 2

    public FastingB() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        baseSecondMagic = secondMagic = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new ThornsPower(p, secondMagic));
        applyToSelf(new TakeItEasyPower());
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(3);
    }
}