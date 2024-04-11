package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;

import static theFishing.util.Wiz.att;

public class LandOfGiants extends AbstractBoard {
    public static final String ID = FishingMod.makeID(LandOfGiants.class.getSimpleName());

    public LandOfGiants() {
        super(ID);
    }

    @Override
    public void effect() {
        att(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
        att(new GainBlockAction(AbstractDungeon.player, 2));
    }
}
