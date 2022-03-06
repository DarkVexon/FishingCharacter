package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class NoWhammiesFollowUpAction extends AbstractGameAction {
    public NoWhammiesFollowUpAction() {
        this.duration = 0.001F;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
        this.tickDuration();
        if (this.isDone) {
            if (DrawCardAction.drawnCards.stream().anyMatch(c -> c.color == AbstractCard.CardColor.COLORLESS)) {
                addToTop(new GainEnergyAction(1));
            }
        }

    }
}
