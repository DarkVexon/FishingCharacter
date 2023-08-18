package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.powers.MoreDamageThisTurnPower;

import static theFishing.util.Wiz.*;

public class WatchersTemple extends AbstractBoard {
    public static final String ID = FishingMod.makeID(WatchersTemple.class.getSimpleName());

    public WatchersTemple() {
        super(ID);
        effects.add(() -> att(new ScryAction(3)));
        effects.add(() -> makeInHandTop(new Smite()));
        effects.add(() -> applyToSelfTop(new MoreDamageThisTurnPower(1)));
    }
}
