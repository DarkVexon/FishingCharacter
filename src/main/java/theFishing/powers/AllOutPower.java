package theFishing.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;

public class AllOutPower extends AbstractEasyPower {
    public static String ID = makeID(AllOutPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public AllOutPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, true, AbstractDungeon.player, amount);
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
