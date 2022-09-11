package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.util.Wiz;

import java.util.ArrayList;

public class FutureProofingAction extends AbstractGameAction {
    public FutureProofingAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        isDone = true;
        for (int i = 0; i < this.amount; i++) {
            ArrayList<AbstractCard> possCards = new ArrayList<>();
            for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
                if (q.canUpgrade()) {
                    possCards.add(q);
                }
            }
            if (!possCards.isEmpty())
                Wiz.getRandomItem(possCards, AbstractDungeon.cardRandomRng).upgrade();
        }
    }
}
