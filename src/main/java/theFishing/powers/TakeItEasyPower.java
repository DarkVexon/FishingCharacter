package theFishing.powers;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.FishingMod.makeID;

public class TakeItEasyPower extends AbstractEasyPower {
    public static String ID = makeID(TakeItEasyPower.class.getSimpleName());

    public TakeItEasyPower() {
        super("Take it Easy", PowerType.DEBUFF, false, AbstractDungeon.player, 1);
    }

    @Override
    public void onInitialApplication() {
        AbstractDungeon.player.gameHandSize -= 1;
    }

    @Override
    public void stackPower(int stackAmount) {
        AbstractDungeon.player.gameHandSize -= stackAmount;
    }
}
