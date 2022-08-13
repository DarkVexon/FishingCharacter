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
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= amount) {
            flash();
            addToBot(new DrawCardAction(1));
            updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        int remaining = (amount - AbstractDungeon.actionManager.cardsPlayedThisTurn.size());
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + remaining + (powerStrings.DESCRIPTIONS[2]);
    }
}