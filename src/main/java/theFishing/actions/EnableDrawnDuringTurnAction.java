package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theFishing.patch.PreDrawPatch;

public class EnableDrawnDuringTurnAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        PreDrawPatch.DRAWN_DURING_TURN = true;
    }
}
