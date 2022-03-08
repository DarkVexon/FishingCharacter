package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.FishingMod.makeID;

public class AnglerFormPower extends AbstractEasyPower {
    public static String ID = makeID(AnglerFormPower.class.getSimpleName());

    public AnglerFormPower(int amount) {
        super("Angler Form", PowerType.BUFF, false, AbstractDungeon.player, amount);
        isTwoAmount = true;
        amount2 = amount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        amount2 = amount;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (amount2 > 0) {
            flash();
            addToBot(new DrawCardAction(1));
            amount2--;
            updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        description = "The first #b" + amount + " times you play a card each turn, draw a card. NL #b" + amount2 + " triggers remaining this turn.";
    }
}