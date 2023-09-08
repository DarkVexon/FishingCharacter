package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theFishing.FishingMod;
import theFishing.boards.dailies.WizvexTower;

public class RefreshSpellsAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        if (FishingMod.activeBoard instanceof WizvexTower)
            ((WizvexTower) FishingMod.activeBoard).refreshSpells();
    }
}