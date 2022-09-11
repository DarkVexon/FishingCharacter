package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class ReservesPower extends LambdaPower {
    public static String ID = makeID(ReservesPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public ReservesPower() {
        super(makeID("ReservesPower"), powerStrings.NAME, AbstractPower.PowerType.BUFF, false, AbstractDungeon.player, 1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.cost == -1) {
            flash();
            atb(new GainEnergyAction(amount));
        }
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder(powerStrings.DESCRIPTIONS[0]);
        for (int i = 0; i < amount; i++) {
            sb.append("[E] ");
        }
        sb.append(LocalizedStrings.PERIOD);
        description = sb.toString();
    }
}
