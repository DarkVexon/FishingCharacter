package theFishing.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;

public class MintConditionPower extends AbstractEasyPower {
    public static String ID = makeID(MintConditionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public MintConditionPower() {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, 1);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (isFoil(card) && AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)) {
            return type == DamageInfo.DamageType.NORMAL ? damage + ((float) AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount * amount) : damage;
        }
        return damage;
    }

    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (isFoil(card) && AbstractDungeon.player.hasPower(DexterityPower.POWER_ID)) {
            return blockAmount + ((float) AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount * amount);
        }
        return blockAmount;
    }

    @Override
    public void updateDescription() {
        if (amount < 4) {
            description = powerStrings.DESCRIPTIONS[amount - 1];
        } else {
            description = powerStrings.DESCRIPTIONS[4] + amount + powerStrings.DESCRIPTIONS[5];
        }
    }
}
