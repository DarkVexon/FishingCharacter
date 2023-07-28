package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.FishingMod;
import theFishing.powers.OnCompleteDungeonPower;
import theFishing.powers.OnEnterDungeonPower;

public class EnterTheDungeonAction extends AbstractGameAction {
    @Override
    public void update() {
        FishingMod.activeBoard.proceed();
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnEnterDungeonPower) {
                ((OnEnterDungeonPower) p).onDungeonEnter();
            }
        }
        if (FishingMod.activeBoard.progress == FishingMod.activeBoard.effects.size() - 1) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnCompleteDungeonPower) {
                    ((OnCompleteDungeonPower) p).onDungeonComplete();
                }
            }
        }
        isDone = true;
    }
}
