package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theFishing.FishingMod.makeID;

public class TakeItEasyBlockPower extends AbstractAdventurerPower {
    public static String ID = makeID(TakeItEasyBlockPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public TakeItEasyBlockPower(int amount) {
        super(makeID("TakeItEasyBlockPower"), powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new GainBlockAction(owner, owner, amount));
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
