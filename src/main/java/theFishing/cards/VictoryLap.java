package theFishing.cards;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.cards.AbstractFishingCard;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class VictoryLap extends AbstractFishingCard {
    public final static String ID = makeID("VictoryLap");
    // intellij stuff power, self, uncommon, , , , , , 

    public VictoryLap() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Victory Lap", AbstractPower.PowerType.BUFF, false, p, 2) {


            @Override
            public void updateDescription() {
                description = "Whenever you shuffle your draw pile, add #b" + amount + " #yShivs into your hand.";
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}