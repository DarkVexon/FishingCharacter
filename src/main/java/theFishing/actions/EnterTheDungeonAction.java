package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.FishingMod;
import theFishing.powers.OnCompleteDungeonPower;

public class EnterTheDungeonAction extends AbstractGameAction {
    @Override
    public void update() {
        if (FishingMod.activeBoard.progress == 2) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnCompleteDungeonPower) {
                    ((OnCompleteDungeonPower) p).onDungeonComplete();
                }
            }
        }
        FishingMod.activeBoard.proceed();
        addToTop(new AnimateHopAction(AbstractDungeon.player));
        isDone = true;
    }
}
