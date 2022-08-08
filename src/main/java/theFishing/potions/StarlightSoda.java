package theFishing.potions;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.FishingMod;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class StarlightSoda extends AbstractPotion {
    public static final String POTION_ID = makeID("StarlightSoda");

    public StarlightSoda() {
        super("Starlight Soda", POTION_ID, PotionRarity.UNCOMMON, PotionSize.SPIKY, PotionColor.ENERGY);
        potency = getPotency();
        description = "Remove your #yWeak, #yVulnerable, and #yFrail. #yScry #b" + potency + ".";
        labOutlineColor = FishingMod.characterColor;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = "Remove your #yWeak, #yVulnerable, and #yFrail. #yScry #b" + potency + ".";
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        atb(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, WeakPower.POWER_ID));
        atb(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, VulnerablePower.POWER_ID));
        atb(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, FrailPower.POWER_ID));
        atb(new ScryAction(potency));
    }

    @Override
    public int getPotency(int ascensionlevel) {
        return 3;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new StarlightSoda();
    }
}
