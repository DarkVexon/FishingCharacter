package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.MultiCast;
import com.megacrit.cardcrawl.cards.blue.Tempest;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class XMarksTheSpot extends AbstractFishingCard {
    public final static String ID = makeID("XMarksTheSpot");
    // intellij stuff skill, self, uncommon, , , , , , 

    public XMarksTheSpot() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = CardLibrary.getCard(getRandomItem(getCardsMatchingPredicate(c -> c.cost == -1 && c.color != CardColor.COLORLESS && !c.cardID.equals(MultiCast.ID), true)));
        if (q.cardID.equals(Tempest.ID) && p.maxOrbs == 0) {
            addToBot(new IncreaseMaxOrbAction(5));
            atb(new TalkAction(true, "orb? :0", 0.5F, 2F));
        }
        makeInHand(q);
        atb(new GainEnergyAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}