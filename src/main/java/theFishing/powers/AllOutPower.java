package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static theFishing.FishingMod.makeID;

public class AllOutPower extends AbstractAdventurerPower implements OnRefreshHandPower {
    public static String ID = makeID(AllOutPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public AllOutPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    public int activatedThisTurn = 0;
    private boolean disabledUntilEndOfTurn = false;

    @Override
    public void atStartOfTurnPostDraw() {
        activatedThisTurn = 0;
        disabledUntilEndOfTurn = false;
        updateDescription();
    }

    @Override
    public void onRefreshHand() {
        if (canTrigger()) {
            activatedThisTurn += 1;
            this.flash();
            addToBot(new DrawCardAction(1));
            updateDescription();
        }
    }

    public void disableUntilTurnEnds() {
        this.disabledUntilEndOfTurn = true;
    }

    private boolean canTrigger() {
        return AbstractDungeon.actionManager.actions.isEmpty() && AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.actionManager.turnHasEnded && activatedThisTurn < amount && !AbstractDungeon.isScreenUp && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !this.disabledUntilEndOfTurn;
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        if (amount == 1) {
            sb.append(powerStrings.DESCRIPTIONS[0]);
        }
        else {
            sb.append(powerStrings.DESCRIPTIONS[1] + amount + powerStrings.DESCRIPTIONS[2]);
        }
        sb.append(powerStrings.DESCRIPTIONS[3]);
        int left = amount - activatedThisTurn;
        sb.append(left);
        if (left == 1) {
            sb.append(powerStrings.DESCRIPTIONS[4]);
        }
        else {
            sb.append(powerStrings.DESCRIPTIONS[5]);
        }
        sb.append(powerStrings.DESCRIPTIONS[6]);
        description = sb.toString();
    }
}
