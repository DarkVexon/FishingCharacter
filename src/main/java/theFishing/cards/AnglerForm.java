package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.AnglerFormPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class AnglerForm extends AbstractFishingCard {
    public final static String ID = makeID("AnglerForm");
    // intellij stuff power, self, rare, , , , , 3, 1

    public AnglerForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AnglerFormPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}