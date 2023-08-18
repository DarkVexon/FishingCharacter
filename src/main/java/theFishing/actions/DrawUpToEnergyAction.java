package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.util.Wiz;

public class DrawUpToEnergyAction extends AbstractGameAction {
    private final int energy;
    private int tracker = 0;

    public DrawUpToEnergyAction(int energy) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.energy = energy;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (tracker >= energy || (AbstractDungeon.player.drawPile.isEmpty() && AbstractDungeon.player.discardPile.isEmpty()) || AbstractDungeon.player.hand.size() >= 10) {
                this.isDone = true;
                return;
            }

            if (AbstractDungeon.player.hasPower("No Draw")) {
                AbstractDungeon.player.getPower("No Draw").flash();
                this.isDone = true;
                return;
            }

            if (!AbstractDungeon.player.drawPile.isEmpty()) {
                AbstractCard c = AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - 1);
                if (Wiz.getLogicalCardCost(c) > 0)
                    tracker += c.cost;

                this.addToTop(new DrawUpToEnergyAction(energy - tracker));
                this.addToTop(new DrawCardAction(1));
            } else {
                this.addToTop(new DrawUpToEnergyAction(energy - tracker));
                this.addToTop(new EmptyDeckShuffleAction());
            }

            this.isDone = true;
            return;
        }

        this.tickDuration();
    }
}
