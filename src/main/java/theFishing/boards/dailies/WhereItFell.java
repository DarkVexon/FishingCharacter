package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cards.StarShard;

import static theFishing.util.Wiz.*;

public class WhereItFell extends AbstractBoard {
    public static final String ID = FishingMod.makeID(WhereItFell.class.getSimpleName());

    public WhereItFell() {
        super(ID);
        effects.add(() -> makeInHandTop(new StarShard()));
        effects.add(() -> makeInHandTop(new StarShard()));
        effects.add(() -> att(new DrawCardAction(2)));
    }
}
