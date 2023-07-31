package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.MintConditionPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class MintCondition extends AbstractFishingCard {
    public final static String ID = makeID("MintCondition");
    // intellij stuff power, self, uncommon, , , , , , 

    public MintCondition() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MintConditionPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}