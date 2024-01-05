package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;

public class EnterTheDungeonAction extends AbstractGameAction {

    public static int timesDelvedThisCombat = 0;

    @Override
    public void update() {
        FishingMod.activeBoard.proceed();
        timesDelvedThisCombat++;
        addToTop(new AnimateHopAction(AbstractDungeon.player));
        isDone = true;
    }
}
