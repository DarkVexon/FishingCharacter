package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.att;

public class TombOfRorrim extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TombOfRorrim.class.getSimpleName());

    public TombOfRorrim() {
        super(ID);
        effects.add(() -> att(new GainBlockAction(AbstractDungeon.player, 3)));
        effects.add(() -> att(new GainBlockAction(AbstractDungeon.player, 3)));
        effects.add(() -> applyToSelfTop(new DuplicationPower(AbstractDungeon.player, 1)));
    }
}
