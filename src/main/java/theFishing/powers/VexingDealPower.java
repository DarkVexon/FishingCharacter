package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theFishing.FishingMod.makeID;

public class VexingDealPower extends AbstractAdventurerPower {
    public static String ID = makeID(VexingDealPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public VexingDealPower() {
        super(ID, powerStrings.NAME, AbstractPower.PowerType.BUFF, false, AbstractDungeon.player, 1);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new DrawCardAction(amount));
        addToBot(new ExhaustAction(amount, false, false));
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(powerStrings.DESCRIPTIONS[0]);
        sb.append(amount);
        if (amount == 1) {
            sb.append(powerStrings.DESCRIPTIONS[1]);
        } else {
            sb.append(powerStrings.DESCRIPTIONS[2]);
        }
        sb.append(powerStrings.DESCRIPTIONS[3]);
        sb.append(amount);
        if (amount == 1) {
            sb.append(powerStrings.DESCRIPTIONS[1]);
        } else {
            sb.append(powerStrings.DESCRIPTIONS[2]);
        }
        description = sb.toString();
    }
}
