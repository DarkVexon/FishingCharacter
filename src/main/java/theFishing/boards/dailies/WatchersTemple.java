package theFishing.boards.dailies;

import com.megacrit.cardcrawl.cards.tempCards.Smite;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.powers.MoreDamageThisTurnPower;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.makeInHandTop;

public class WatchersTemple extends AbstractBoard {
    public static final String ID = FishingMod.makeID(WatchersTemple.class.getSimpleName());

    public WatchersTemple() {
        super(ID);
        effects.add(() -> makeInHandTop(new Smite()));
        effects.add(() -> makeInHandTop(new Smite()));
        effects.add(() -> applyToSelfTop(new MoreDamageThisTurnPower(1)));
    }
}
