package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;

import static theFishing.util.Wiz.*;

public class TombOfRorrim extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TombOfRorrim.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public TombOfRorrim() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> att(new GainBlockAction(AbstractDungeon.player, 1))));
        effects.add(new BoardEffect(TEXT[2], () -> att(new GainBlockAction(AbstractDungeon.player, 3))));
        effects.add(new BoardEffect(TEXT[3], () -> applyToSelfTop(new DuplicationPower(AbstractDungeon.player, 1))));
    }
}
