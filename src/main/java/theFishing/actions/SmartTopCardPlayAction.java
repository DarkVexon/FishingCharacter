package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.util.Wiz.att;

public class SmartTopCardPlayAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        att(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
    }
}
