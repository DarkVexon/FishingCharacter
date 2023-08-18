package theFishing.boards.dailies;

import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cards.Kraken;
import theFishing.powers.MoreKrakenDamagePower;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.shuffleIn;

public class TheDeep extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheDeep.class.getSimpleName());

    public TheDeep() {
        super(ID);
        effects.add(() -> applyToSelfTop(new MoreKrakenDamagePower(10)));
        effects.add(() -> applyToSelfTop(new MoreKrakenDamagePower(10)));
        effects.add(() -> applyToSelfTop(new MoreKrakenDamagePower(10)));
    }

    public void atBattleStartPreDraw() {
        shuffleIn(new Kraken());
    }
}
