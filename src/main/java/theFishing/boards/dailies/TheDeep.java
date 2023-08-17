package theFishing.boards.dailies;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cards.Kraken;
import theFishing.powers.MoreKrakenDamagePower;

import static theFishing.util.Wiz.*;

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
