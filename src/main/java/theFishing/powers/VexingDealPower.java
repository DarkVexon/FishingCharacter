package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;

public class VexingDealPower extends AbstractEasyPower {
    public static String ID = makeID(VexingDealPower.class.getSimpleName());

    public VexingDealPower(int amount, int amount2) {
        super("Vexing Deal", PowerType.BUFF, false, AbstractDungeon.player, amount);
        isTwoAmount = true;
        this.amount2 = amount2;
        updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.CURSE || card.color == AbstractCard.CardColor.CURSE) {
            flash();
            addToBot(new DrawCardAction(amount));
            addToBot(new AllEnemyLoseHPAction(amount2));
        }
    }

    @Override
    public void updateDescription() {
        description = "Whenever you draw a #rCurse card, draw #b" + amount + (amount == 1 ? " additional card" : " additional cards") + " and ALL enemies lose #b" + amount2 + " HP.";
    }
}
