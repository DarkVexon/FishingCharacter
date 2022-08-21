package theFishing.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class CollectorPower extends AbstractEasyPower {
    public static String ID = makeID(CollectorPower.class.getSimpleName());
    private static PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public CollectorPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, true, AbstractDungeon.player, amount);
    }

    public boolean activated = true;

    @Override
    public void atStartOfTurn() {
        activated = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        boolean triggered = false;
        if (FoilPatches.isFoil(card) && !activated) {
            flash();
            applyToSelf(new StrengthPower(owner, amount));
            triggered = true;
        }
        if (card.rarity == AbstractCard.CardRarity.RARE && !activated) {
            if (!triggered)
                flash();
            applyToSelf(new VigorPower(owner, amount * 3));
        }
        activated = true;
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + amount * 3 + powerStrings.DESCRIPTIONS[2];
    }
}
