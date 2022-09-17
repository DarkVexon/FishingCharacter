package theFishing.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.actions.AllOutProcAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class AllOutPower extends AbstractAdventurerPower {
    public static String ID = makeID(AllOutPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public AllOutPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        atb(new AllOutProcAction(amount));
    }


    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
