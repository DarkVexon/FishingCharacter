package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

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

    public void triggerOnGlowCheck() {
        this.glowColor = isVoyaged() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}