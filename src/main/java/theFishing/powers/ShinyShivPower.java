package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class ShinyShivPower extends AbstractAdventurerPower {
    public static String ID = makeID(ShinyShivPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public ShinyShivPower() {
        super(ID, powerStrings.NAME, PowerType.BUFF, true, AbstractDungeon.player, 1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (FoilPatches.isFoil(card)) {
            flash();
            atb(new MakeTempCardInHandAction(new Shiv(), amount, true));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + (amount == 1 ? powerStrings.DESCRIPTIONS[1] : powerStrings.DESCRIPTIONS[2]) + powerStrings.DESCRIPTIONS[3];
    }
}
