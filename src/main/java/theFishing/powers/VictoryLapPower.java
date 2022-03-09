package theFishing.powers;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.runHistory.RunHistoryScreen;
import com.megacrit.cardcrawl.screens.stats.RunData;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;

public class VictoryLapPower extends AbstractEasyPower implements OnShufflePower {
    public static String ID = makeID(VictoryLapPower.class.getSimpleName());

    public static int upgraded = -99;

    public static boolean upgraded() {
        if (upgraded == -99) {
            RunHistoryScreen rhs = new RunHistoryScreen();
            rhs.refreshData();
            ArrayList<RunData> unfilteredRuns = ReflectionHacks.getPrivate(rhs, RunHistoryScreen.class, "unfilteredRuns");
            upgraded = unfilteredRuns.get(0).victory ? 1 : 0;
        }
        return upgraded == 1 ? true : false;
    }

    public VictoryLapPower(int amount) {
        super("Victory Lap", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void updateDescription() {
        description = "When you shuffle your draw pile, add #b" + amount + (upgraded() ? " #yUpgraded" : " ") + (amount == 1 ? "#yShiv" : "#yShivs") + " into your hand.";
    }

    @Override
    public void onShuffle() {
        flash();
        AbstractCard q = new Shiv();
        if (upgraded()) {
            q.upgrade();
        }
        Wiz.makeInHand(q, amount);
    }
}
