package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.ItWasTHIIIIIIISBigAction;

import static theFishing.FishingMod.makeID;

public class ItWasTHIIIIIIISBig extends AbstractFishingCard {
    public final static String ID = makeID(ItWasTHIIIIIIISBig.class.getSimpleName());

    public ItWasTHIIIIIIISBig() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ItWasTHIIIIIIISBigAction(magicNumber));
    }

    @Override
    public float getTitleFontSize() {
        return 18F;
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}