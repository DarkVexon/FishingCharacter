package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;

public class MintConditionPower extends AbstractAdventurerPower {
    public static String ID = makeID(MintConditionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public MintConditionPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    private boolean triggered = false;

    @Override
    public void atStartOfTurn() {
        triggered = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (FoilPatches.isFoil(card) && !triggered) {
            flash();
            triggered = true;
            addToBot(new DamageAction(Wiz.getFrontmostEnemy(), new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS)));
        }
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + amount + powerStrings.DESCRIPTIONS[2];
    }
}
