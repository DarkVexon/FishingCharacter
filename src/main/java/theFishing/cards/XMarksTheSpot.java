package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.TheFishing;
import theFishing.cards.AbstractFishingCard;

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
        AbstractCard q = getRandomItem(getCardsMatchingPredicate(c -> c.cost == -1 && c.color != TheFishing.Enums.FISHING_COLOR, true));
        makeInHand(q);
        if (upgraded) {
            atb(new GainEnergyAction(1));
        }
    }

    public void upp() {
        uDesc();
    }
}