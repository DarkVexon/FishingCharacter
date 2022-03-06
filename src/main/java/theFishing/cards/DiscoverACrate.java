package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class DiscoverACrate extends AbstractFishingCard {
    public final static String ID = makeID("DiscoverACrate");
    // intellij stuff skill, self, uncommon, , , , , , 

    public DiscoverACrate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        cardToPreview.add(new Smite());
        cardToPreview.add(new Safety());
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard sm = new Smite();
        AbstractCard sa = new Safety();
        if (isVoyaged()) {
            sm.upgrade();
            sa.upgrade();
        }
        makeInHand(sm);
        makeInHand(sa);
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}