package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class GlogusEgg extends AbstractFishingCard {
    public final static String ID = makeID("GlogusEgg");
    // intellij stuff skill, none, common, , , , , , 

    public GlogusEgg() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        AbstractCard c = new Glogus();
        FoilPatches.makeFoil(c);
        cardsToPreview = c;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnExhaust() {
        CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.1F);
        AbstractCard c = new Glogus();
        if (upgraded) {
            c.upgrade();
        }
        FoilPatches.makeFoil(c);
        makeInHand(c);
    }

    public void upp() {
        AbstractCard c = new Glogus();
        c.upgrade();
        FoilPatches.makeFoil(c);
        cardsToPreview = c;
        uDesc();
    }
}