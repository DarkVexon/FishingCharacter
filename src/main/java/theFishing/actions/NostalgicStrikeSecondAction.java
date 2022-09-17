package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NostalgicStrikeSecondAction extends AbstractGameAction {
    private final AbstractCard card;

    public NostalgicStrikeSecondAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        isDone = true;
        AbstractDungeon.player.hand.addToHand(card);
        card.lighten(false);
        AbstractDungeon.player.discardPile.removeCard(card);
        AbstractDungeon.player.hand.refreshHandLayout();
    }
}
