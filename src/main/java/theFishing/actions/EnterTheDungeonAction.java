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
        FishingMod.activeBoard.proceed();
        if (FishingMod.activeBoard.progress == FishingMod.activeBoard.effects.size()) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnCompleteDungeonPower) {
                    ((OnCompleteDungeonPower) p).onDungeonComplete();
                }
            }
        }
        addToTop(new AnimateHopAction(AbstractDungeon.player));
        isDone = true;
    }
}
