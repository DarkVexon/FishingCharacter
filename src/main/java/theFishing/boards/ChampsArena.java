package theFishing.boards;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.makeInHandTop;

public class ChampsArena extends AbstractBoard {
    public static final String ID = "fishing:ChampsArena";
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public ChampsArena() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> makeInHandTop(new Shiv())));
        effects.add(new BoardEffect(TEXT[1], () -> makeInHandTop(new Shiv())));
        effects.add(new BoardEffect(TEXT[2], () -> applyToSelfTop(new StrengthPower(AbstractDungeon.player, 1))));
    }
}
