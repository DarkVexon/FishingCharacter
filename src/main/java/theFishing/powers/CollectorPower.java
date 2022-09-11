package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.util.Wiz.atb;

public class CollectorPower extends AbstractEasyPower {
    public static String ID = makeID(CollectorPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public CollectorPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(q -> isFoil(q)).count() >= 2) {
            flash();
            atb(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
        }
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        updateDescription();
    }

    @Override
    public void updateDescription() {
        int cards = (int) AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(q -> isFoil(q)).count();
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + cards + ((cards == 1) ? powerStrings.DESCRIPTIONS[2] : powerStrings.DESCRIPTIONS[3]);
    }
}
