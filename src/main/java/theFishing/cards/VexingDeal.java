package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class VexingDeal extends AbstractFishingCard {
    public final static String ID = makeID("VexingDeal");
    // intellij stuff power, self, rare, , , , , 33, 11

    public VexingDeal() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Vexing Deal", AbstractPower.PowerType.BUFF, false, p, 1) {

            @Override
            public void atStartOfTurnPostDraw() {
                addToBot(new ExhaustAction(amount, false, false));
                addToBot(new DrawCardAction(amount));
            }

            @Override
            public void updateDescription() {
                description = "At the start of your turn, #yExhaust #b" + amount + " " + (amount == 1 ? "card" : "cards") + " and draw #b" + amount + " " + (amount == 1 ? "card" : "cards") + ".";
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}