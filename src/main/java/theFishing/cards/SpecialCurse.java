package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class SpecialCurse extends AbstractFishingCard {
    public final static String ID = makeID("SpecialCurse");
    // intellij stuff curse, none, curse, , , , , , 

    public SpecialCurse() {
        super(ID, 1, CardType.CURSE, CardRarity.CURSE, CardTarget.NONE, CardColor.CURSE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upgrade() {
    }

    public void upp() {
    }
}