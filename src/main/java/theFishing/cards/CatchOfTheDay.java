package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class CatchOfTheDay extends AbstractFishingCard {
    public final static String ID = makeID("CatchOfTheDay");
    // intellij stuff power, self, uncommon, , , , , , 

    public CatchOfTheDay() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Catch of the Day", AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void atEndOfTurn(boolean isPlayer) {
                flash();
                for (int i = 0; i < amount; i++) {
                    //TODO: Same VFX/SFX as Cast Line
                    atb(new MakeTempCardInDrawPileAction(AbstractFishCard.returnRandomFish(), 1, true, true));
                }
            }

            @Override
            public void updateDescription() {
                description = "At the end of your turn, shuffle #b" + amount + " #yFish into your draw pile.";
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}