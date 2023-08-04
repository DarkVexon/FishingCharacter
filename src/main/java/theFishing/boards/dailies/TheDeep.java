package theFishing.boards.dailies;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.cards.Kraken;
import theFishing.powers.MoreKrakenDamagePower;

import static theFishing.util.Wiz.*;

public class TheDeep extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheDeep.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public TheDeep() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[2], () -> applyToSelfTop(new MoreKrakenDamagePower(10))));
        effects.add(new BoardEffect(TEXT[2], () -> applyToSelfTop(new MoreKrakenDamagePower(10))));
        effects.add(new BoardEffect(TEXT[2], () -> applyToSelfTop(new MoreKrakenDamagePower(10))));
    }

    public void atBattleStartPreDraw() {
        shuffleIn(new Kraken());
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }
}
