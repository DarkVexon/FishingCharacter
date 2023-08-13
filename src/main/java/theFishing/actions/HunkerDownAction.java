package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.util.Wiz.att;

public class HunkerDownAction extends AbstractGameAction {

    public HunkerDownAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        isDone = true;
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1 < amount) {
            att(new GainEnergyAction(1));
        }
    }
}
