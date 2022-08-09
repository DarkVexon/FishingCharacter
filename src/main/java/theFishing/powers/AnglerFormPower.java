package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theFishing.FishingMod.makeID;

public class AnglerFormPower extends AbstractEasyPower {
    public static String ID = makeID(AnglerFormPower.class.getSimpleName());

    private static PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public AnglerFormPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
        isTwoAmount = true;
        amount2 = amount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        amount2 = amount;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        amount2 += stackAmount;
        super.stackPower(stackAmount);
        updateDescription();
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
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + amount2 + powerStrings.DESCRIPTIONS[2];
    }
}