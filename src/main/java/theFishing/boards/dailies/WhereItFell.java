package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.cards.StarShard;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHandTop;

public class WhereItFell extends AbstractBoard {
    public static final String ID = FishingMod.makeID(WhereItFell.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public WhereItFell() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> makeInHandTop(new StarShard())));
        effects.add(new BoardEffect(TEXT[1], () -> makeInHandTop(new StarShard())));
        effects.add(new BoardEffect(TEXT[2], () -> atb(new DrawCardAction(3))));
    }
}
