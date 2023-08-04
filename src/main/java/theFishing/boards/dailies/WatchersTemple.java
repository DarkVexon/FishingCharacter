package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.powers.MoreDamageThisTurnPower;

import static theFishing.util.Wiz.*;

public class WatchersTemple extends AbstractBoard {
    public static final String ID = FishingMod.makeID(WatchersTemple.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public WatchersTemple() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> att(new ScryAction(3))));
        effects.add(new BoardEffect(TEXT[2], () -> makeInHandTop(new Smite())));
        effects.add(new BoardEffect(TEXT[3], () -> applyToSelfTop(new MoreDamageThisTurnPower(1))));
    }
}
