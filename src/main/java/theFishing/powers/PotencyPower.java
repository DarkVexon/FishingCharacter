package theFishing.powers;

import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;

public class PotencyPower extends AbstractEasyPower {
    public static String ID = makeID(PotencyPower.class.getSimpleName());

    public PotencyPower(int amount) {
        super("Potency", AbstractPower.PowerType.BUFF, true, AbstractDungeon.player, amount);
    }

    @Override
    public void updateDescription() {
        description = "X in your cards are increased by #b" + amount + ".";
    }
}
