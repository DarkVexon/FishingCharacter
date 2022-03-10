package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.MultiCast;
import com.megacrit.cardcrawl.cards.blue.Tempest;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = getRandomItem(getCardsMatchingPredicate(c -> c.cost == -1 && c.color != TheFishing.Enums.FISHING_COLOR && c.color != CardColor.COLORLESS && !c.cardID.equals(MultiCast.ID), true));
        if (q.cardID.equals(Tempest.ID)) {
            addToBot(new IncreaseMaxOrbAction(6));
            atb(new TalkAction(true, "orb? :0", 2F, 2F));
        }
        makeInHand(q);
        if (upgraded) {
            atb(new GainEnergyAction(1));
        }
    }

    public void upp() {
        uDesc();
    }
}