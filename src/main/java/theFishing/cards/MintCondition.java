package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.powers.MintConditionPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class MintCondition extends AbstractFishingCard {
    public final static String ID = makeID("MintCondition");
    // intellij stuff self, , uncommon, , , , , 2, 1

    public MintCondition() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new DexterityPower(p, 1));
        applyToSelf(new MintConditionPower());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}