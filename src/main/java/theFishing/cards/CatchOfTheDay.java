package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.topDeck;

public class CatchOfTheDay extends AbstractFishingCard {
    public final static String ID = makeID("CatchOfTheDay");
    // intellij stuff power, self, uncommon, , , , , , 

    public CatchOfTheDay() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("CatchOfTheDayPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void atEndOfTurn(boolean isPlayer) {
                flash();
                for (int i = 0; i < amount; i++) {
                    topDeck(AbstractFishCard.returnRandomFish());
                }
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + cardStrings.EXTENDED_DESCRIPTION[2];
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}