package theFishing.boards.dailies;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.powers.ArenaStackPower;

import static theFishing.util.Wiz.applyToSelfTop;

public class ChampsArena extends AbstractBoard {
    public static final String ID = FishingMod.makeID(ChampsArena.class.getSimpleName());

    public ChampsArena() {
        super(ID);
    }

    @Override
    public void effect() {
        applyToSelfTop(new ArenaStackPower(1));
    }
}
