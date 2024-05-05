package theFishing.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RandomCardFromDiscardPileToHandAction2 extends AbstractGameAction {
    private AbstractPlayer p;

    public RandomCardFromDiscardPileToHandAction2() {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.p.discardPile.size() > 0 && AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
            AbstractCard card = this.p.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
            this.p.hand.addToHand(card);
            card.lighten(false);
            this.p.discardPile.removeCard(card);
            this.p.hand.refreshHandLayout();
        }

        this.tickDuration();
        this.isDone = true;
    }
}
