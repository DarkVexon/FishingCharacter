package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.util.Wiz.att;

public class ZaHandoAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        if (!AbstractDungeon.player.hand.isEmpty()) {
            if (AbstractDungeon.player.hand.size() % 2 == 0) {
                att(new ExhaustSpecificCardAction(AbstractDungeon.player.hand.group.get(AbstractDungeon.player.hand.group.size() / 2), AbstractDungeon.player.hand));
                att(new ExhaustSpecificCardAction(AbstractDungeon.player.hand.group.get(AbstractDungeon.player.hand.group.size() / 2 - 1), AbstractDungeon.player.hand));
            } else {
                att(new ExhaustSpecificCardAction(AbstractDungeon.player.hand.group.get(AbstractDungeon.player.hand.group.size() / 2), AbstractDungeon.player.hand));
            }
        }
    }
}
