package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Turtle extends AbstractFishingCard {
    public final static String ID = makeID(Turtle.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , 5, 2

    public Turtle() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}