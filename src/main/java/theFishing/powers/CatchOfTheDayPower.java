package theFishing.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;

public class CatchOfTheDayPower extends AbstractAdventurerPower {
    public static String ID = makeID(CatchOfTheDayPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public CatchOfTheDayPower() {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, 1);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        for (int i = 0; i < amount; i++) {
            shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
