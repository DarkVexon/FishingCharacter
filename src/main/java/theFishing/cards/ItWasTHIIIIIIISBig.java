package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.ItWasTHIIIIIIISBigAction;

import static theFishing.FishingMod.makeID;

public class ItWasTHIIIIIIISBig extends AbstractFishingCard {
    public final static String ID = makeID(ItWasTHIIIIIIISBig.class.getSimpleName());

    public ItWasTHIIIIIIISBig() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ItWasTHIIIIIIISBigAction(magicNumber));
    }

    public void upp() {
        upMagic(1);
        uDesc();
    }
}