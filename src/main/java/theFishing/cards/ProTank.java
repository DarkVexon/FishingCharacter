package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;
import theFishing.powers.ProTankPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ProTank extends AbstractFishingCard {
    public final static String ID = makeID("ProTank");
    // intellij stuff power, self, uncommon, , , , , 3, 1

    public ProTank() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ProTankPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}