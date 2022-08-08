package theFishing.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;

public class TripwirePower extends AbstractEasyPower implements OnMyBlockBrokenPower {
    public static String ID = makeID(TripwirePower.class.getSimpleName());
    private static PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public TripwirePower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, true, AbstractDungeon.player, amount);
    }

    @Override
    public void onMyBlockBroken() {
        flash();
        addToBot(new AnimateHopAction(owner));
        addToBot(new AllEnemyLoseHPAction(amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
