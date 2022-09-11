package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.util.Wiz.att;

public class MaybeLaterAction extends AbstractGameAction {
    public MaybeLaterAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        isDone = true;
        if (!AbstractDungeon.player.hand.isEmpty()) {
            att(new GainBlockAction(AbstractDungeon.player, amount));
        }
        att(new PutOnDeckAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));
    }
}
