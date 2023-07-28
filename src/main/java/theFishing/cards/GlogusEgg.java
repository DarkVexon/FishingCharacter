package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class GlogusEgg extends AbstractFishingCard {
    public final static String ID = makeID("GlogusEgg");
    // intellij stuff skill, none, common, , , , , , 

    public GlogusEgg() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        cardsToPreview = new Glogus();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard c = new Glogus();
        if (upgraded) {
            c.upgrade();
        }
        makeInHand(c);
    }

    public void upp() {
        AbstractCard c = new Glogus();
        c.upgrade();
        cardsToPreview = c;
    }
}