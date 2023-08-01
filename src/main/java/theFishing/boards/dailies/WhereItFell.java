package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.cards.StarShard;
import theFishing.powers.BetterStarShardsThisTurnPower;

import static theFishing.util.Wiz.*;

public class WhereItFell extends AbstractBoard {
    public static final String ID = FishingMod.makeID(WhereItFell.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public WhereItFell() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> makeInHand(new StarShard())));
        effects.add(new BoardEffect(TEXT[1], () -> makeInHand(new StarShard())));
        effects.add(new BoardEffect(TEXT[2], () -> applyToSelf(new BetterStarShardsThisTurnPower(6))));
    }
}
