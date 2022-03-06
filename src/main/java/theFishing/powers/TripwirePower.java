package theFishing.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;

public class TripwirePower extends AbstractEasyPower implements OnMyBlockBrokenPower {
    public static String ID = makeID(TripwirePower.class.getSimpleName());

    public TripwirePower(int amount) {
        super("Tripwire", PowerType.BUFF, true, AbstractDungeon.player, amount);
    }

    @Override
    public void onMyBlockBroken() {
        flash();
        addToBot(new AllEnemyLoseHPAction(AbstractDungeon.player, amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    @Override
    public void updateDescription() {
        description = "If your #yBlock is broken this turn, the attacker loses #b" + amount + " HP.";
    }
}
