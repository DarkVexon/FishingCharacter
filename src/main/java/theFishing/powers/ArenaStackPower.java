package theFishing.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theFishing.FishingMod.makeID;

public class ArenaStackPower extends AbstractAdventurerPower {
    public static String ID = makeID(ArenaStackPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public ArenaStackPower() {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, -1);
        canGoNegative = false;
    }

    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0];
    }
}