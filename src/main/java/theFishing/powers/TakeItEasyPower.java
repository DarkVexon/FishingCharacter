package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theFishing.FishingMod.makeID;

public class TakeItEasyPower extends AbstractAdventurerPower {
    public static String ID = makeID(TakeItEasyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public TakeItEasyPower() {
        super(makeID("TakeItEasyPower"), powerStrings.NAME, PowerType.DEBUFF, false, AbstractDungeon.player, 1);
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new DiscardAction(this.owner, this.owner, this.amount, false));
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + (amount == 1 ? powerStrings.DESCRIPTIONS[1] : powerStrings.DESCRIPTIONS[2]);
    }
}
