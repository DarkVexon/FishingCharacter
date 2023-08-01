package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;

import static theFishing.util.Wiz.atb;

public class TheLibrary extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheLibrary.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public TheLibrary() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> atb(new DrawCardAction(1))));
        effects.add(new BoardEffect(TEXT[1], () -> atb(new DrawCardAction(1))));
        effects.add(new BoardEffect(TEXT[2], () -> atb(new DrawCardAction(2))));
    }
}
