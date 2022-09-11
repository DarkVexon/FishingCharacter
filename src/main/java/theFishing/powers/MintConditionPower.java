package theFishing.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;

public class MintConditionPower extends AbstractAdventurerPower {
    public static String ID = makeID(MintConditionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public MintConditionPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (isFoil(card)) {
            return type == DamageInfo.DamageType.NORMAL ? damage + amount : damage;
        }
        return damage;
    }

    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (isFoil(card)) {
            return blockAmount + ((float) amount);
        }
        return blockAmount;
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + amount + powerStrings.DESCRIPTIONS[2];
    }
}
