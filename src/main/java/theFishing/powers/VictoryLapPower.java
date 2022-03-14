package theFishing.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.cards.DoubleShiv;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;

public class VictoryLapPower extends AbstractEasyPower implements OnShufflePower, NonStackablePower {
    public static String ID = makeID(VictoryLapPower.class.getSimpleName());

    private boolean plus;

    public VictoryLapPower(int amount, boolean plus) {
        super("Victory Lap", PowerType.BUFF, false, AbstractDungeon.player, amount);
        this.plus = plus;
    }

    @Override
    public void updateDescription() {
        description = "When you shuffle your draw pile, add #b" + amount + (plus ? " #yUpgraded" : " ") + (amount == 1 ? "#yTwin #yShiv" : "#yTwin #yShivs") + " into your hand.";
    }

    @Override
    public boolean isStackable(AbstractPower power) {
        if (power instanceof VictoryLapPower) {
            return plus == ((VictoryLapPower) power).plus;
        }
        return false;
    }

    @Override
    public void onShuffle() {
        flash();
        AbstractCard q = new DoubleShiv();
        if (plus) {
            q.upgrade();
        }
        Wiz.makeInHand(q, amount);
    }
}
