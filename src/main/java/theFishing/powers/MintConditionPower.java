package theFishing.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class MintConditionPower extends AbstractAdventurerPower {
    public static String ID = makeID(MintConditionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public MintConditionPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (FoilPatches.isFoil(card) && card.rarity.equals(AbstractCard.CardRarity.RARE)) {
            flash();
            applyToSelf(new StrengthPower(owner, amount * 2));
        } else if (FoilPatches.isFoil(card) || card.rarity.equals(AbstractCard.CardRarity.RARE)) {
            flash();
            applyToSelf(new StrengthPower(owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + amount * 2 + powerStrings.DESCRIPTIONS[2];
    }
}
