package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theFishing.FishingMod.makeID;

public class DiscardNextTurnPower extends AbstractAdventurerPower {
    public static String ID = makeID(DiscardNextTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public DiscardNextTurnPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.DEBUFF, true, AbstractDungeon.player, amount);
    }

    public void atStartOfTurnPostDraw() {
        addToBot(new DiscardAction(owner, owner, amount, false));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + (amount == 1 ? powerStrings.DESCRIPTIONS[1] : powerStrings.DESCRIPTIONS[2]);
    }
}