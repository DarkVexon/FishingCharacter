package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
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
        att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }
}
