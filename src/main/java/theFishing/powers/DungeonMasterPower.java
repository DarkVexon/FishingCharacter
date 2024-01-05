package theFishing.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.actions.EnterTheDungeonAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class DungeonMasterPower extends AbstractAdventurerPower {
    public static String ID = makeID(DungeonMasterPower.class.getSimpleName());

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public DungeonMasterPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        for (int i = 0; i < amount; i++) {
            atb(new EnterTheDungeonAction());
        }
    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? powerStrings.DESCRIPTIONS[2] : (powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1]);
    }
}