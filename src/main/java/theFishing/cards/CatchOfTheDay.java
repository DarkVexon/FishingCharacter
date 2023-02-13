package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.CatchOfTheDayPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class CatchOfTheDay extends AbstractFishingCard {
    public final static String ID = makeID("CatchOfTheDay");
    // intellij stuff power, self, uncommon, , , , , , 

    public CatchOfTheDay() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CatchOfTheDayPower());
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}