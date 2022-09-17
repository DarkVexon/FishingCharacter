package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Tempest;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.XMarksTheSpotAction;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.getCardsMatchingPredicate;

public class XMarksTheSpot extends AbstractFishingCard {
    public final static String ID = makeID("XMarksTheSpot");
    // intellij stuff skill, self, uncommon, , , , , ,

    private static ArrayList<String> validCards;

    public XMarksTheSpot() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (validCards == null) {
            validCards = new ArrayList<>();
            validCards.addAll(getCardsMatchingPredicate(c -> c.cost == -1 && c.rarity != CardRarity.BASIC && c.rarity != CardRarity.SPECIAL, true));
        }
        ArrayList<String> possCards = new ArrayList<>();
        possCards.addAll(validCards);
        ArrayList<AbstractCard> cardsList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AbstractCard q = CardLibrary.getCard(possCards.remove(AbstractDungeon.cardRandomRng.random(possCards.size() - 1))).makeCopy();
            if (q instanceof Tempest) {
                q.rawDescription = q.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
                q.initializeDescription();
            }
            cardsList.add(q);
        }
        atb(new XMarksTheSpotAction(cardsList));
        if (upgraded) {
            atb(new GainEnergyAction(1));
        }
    }

    public void upp() {
        uDesc();
    }
}