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
        effects.add(() -> {
            applyToSelfTop(new MoreKrakenDamagePower(10));
            att(new SFXAction("fishing:WAKA_WAKA"));
        });
        effects.add(() -> {
            applyToSelfTop(new MoreKrakenDamagePower(10));
            att(new SFXAction("fishing:WAKA_WAKA"));
        });
        effects.add(() -> {
            applyToSelfTop(new MoreKrakenDamagePower(10));
            att(new SFXAction("fishing:WAKA_WAKA"));
        });
    }

    public void atBattleStartPreDraw() {
        shuffleIn(new Kraken());
    }
}
