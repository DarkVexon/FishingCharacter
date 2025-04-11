package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Rot extends AbstractFishingCard {
    public final static String ID = makeID(Rot.class.getSimpleName());
    // intellij stuff curse, none, special, , , , , , 

    public Rot() {
        super(ID, 1, CardType.CURSE, CardRarity.SPECIAL, CardTarget.NONE, CardColor.CURSE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
    }
}