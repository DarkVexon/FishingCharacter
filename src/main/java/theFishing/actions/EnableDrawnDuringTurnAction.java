package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.patch.PreDrawPatch;

import static theFishing.util.Wiz.att;

public class EnableDrawnDuringTurnAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        PreDrawPatch.DRAWN_DURING_TURN = true;
    }
}
