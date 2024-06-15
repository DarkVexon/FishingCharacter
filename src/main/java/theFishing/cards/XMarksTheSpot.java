package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SneckoField;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.MultiCast;
import com.megacrit.cardcrawl.cards.blue.Tempest;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.Arrays;
import java.util.List;
import theFishing.TheFishing;
import theFishing.FishingMod;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class XMarksTheSpot extends AbstractFishingCard {
    public final static String ID = makeID("XMarksTheSpot");
    private final static List<AbstractCard.CardColor> coloursAvailable = Arrays.asList(AbstractCard.CardColor.RED, AbstractCard.CardColor.BLUE, AbstractCard.CardColor.GREEN, AbstractCard.CardColor.PURPLE, AbstractCard.CardColor.COLORLESS, TheFishing.Enums.FISHING_COLOR);
    // intellij stuff skill, self, uncommon, , , , , , 

    public XMarksTheSpot() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard.CardColor colorr = p.getCardColor();
        AbstractCard q = CardLibrary.getCard(getRandomItem(getCardsMatchingPredicate(c -> c.cost == -1 && !SneckoField.snecko.get(c) && c.color != CardColor.COLORLESS && !c.cardID.equals(MultiCast.ID) && (FishingMod.crossmod ? true : (coloursAvailable.contains(c.color) || c.color.equals(colorr))), true)));
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