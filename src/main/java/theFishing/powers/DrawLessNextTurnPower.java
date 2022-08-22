package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theFishing.FishingMod.makeID;

public class DrawLessNextTurnPower extends AbstractEasyPower {
    public static String ID = makeID(DrawLessNextTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public DrawLessNextTurnPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.DEBUFF, true, AbstractDungeon.player, amount);
        this.loadRegion("lessdraw");
    }

    @Override
    public void onInitialApplication() {
        AbstractDungeon.player.gameHandSize -= amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        AbstractDungeon.player.gameHandSize -= stackAmount;
        super.stackPower(stackAmount);
    }

    @Override
    public void onRemove() {
        AbstractDungeon.player.gameHandSize += amount;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + (amount == 1 ? powerStrings.DESCRIPTIONS[1] : powerStrings.DESCRIPTIONS[2]);
    }
}