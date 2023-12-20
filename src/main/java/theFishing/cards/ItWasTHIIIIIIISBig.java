package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.ItWasTHIIIIIIISBigAction;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class ItWasTHIIIIIIISBig extends AbstractFishingCard {
    public final static String ID = makeID(ItWasTHIIIIIIISBig.class.getSimpleName());

    public ItWasTHIIIIIIISBig() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(AbstractFishCard.returnRandomFish());
        addToBot(new ItWasTHIIIIIIISBigAction(magicNumber));
    }

    @Override
    public float getTitleFontSize() {
        return 18F;
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}