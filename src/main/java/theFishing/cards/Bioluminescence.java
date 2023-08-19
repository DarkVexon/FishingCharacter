package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.BioluminescencePower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Bioluminescence extends AbstractFishingCard {
    public final static String ID = makeID(Bioluminescence.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , 5, 2

    public Bioluminescence() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BioluminescencePower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}