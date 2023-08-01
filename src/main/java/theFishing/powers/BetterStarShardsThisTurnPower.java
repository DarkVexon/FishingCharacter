package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.cards.StarShard;

import static theFishing.FishingMod.makeID;

public class BetterStarShardsThisTurnPower extends AbstractAdventurerPower {
    public static String ID = makeID(BetterStarShardsThisTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public BetterStarShardsThisTurnPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, true, AbstractDungeon.player, amount);
    }

    public void atEndOfRound() {
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return card.cardID.equals(StarShard.ID) ? damage + amount : damage;
    }

    @Override
    public float modifyBlock(float blockAmount, AbstractCard card) {
        return card.cardID.equals(StarShard.ID) ? blockAmount + amount : blockAmount;
    }
}