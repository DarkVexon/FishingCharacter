package theFishing.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class MintConditionPower extends AbstractEasyPower {
    public static String ID = makeID(MintConditionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    private int damage;

    public MintConditionPower(int damage) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, 3);
        this.damage = damage;
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (FoilPatches.isFoil(card)) {
            amount--;
            if (amount == 0) {
                flash();
                amount = 3;
                for (int i = 0; i < damage; i++) {
                    makeInHand(new Shiv());
                }
            }
            this.updateDescription();
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.damage += 1;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + damage + (damage == 1 ? powerStrings.DESCRIPTIONS[1] : powerStrings.DESCRIPTIONS[2]) + powerStrings.DESCRIPTIONS[3];
    }
}
