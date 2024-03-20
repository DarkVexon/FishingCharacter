package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cards.Kraken;
import theFishing.powers.MoreKrakenDamagePower;

import static theFishing.util.Wiz.*;

public class TheDeep extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheDeep.class.getSimpleName());

    public TheDeep() {
        super(ID);
    }

    private boolean activated = false;

    @Override
    public void effect() {
        applyToSelfTop(new MoreKrakenDamagePower(10));
        if (!activated) {
            activated = true;
            shuffleInTop(new Kraken());
        }
        att(new SFXAction("fishing:WAKA_WAKA"));
    }

    public void atBattleStartPreDraw() {
        activated = false;
    }
}
