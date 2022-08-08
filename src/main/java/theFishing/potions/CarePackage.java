package theFishing.potions;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.FishingMod;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class CarePackage extends AbstractPotion {
    public static final String POTION_ID = makeID("CarePackage");

    public CarePackage() {
        super("Care Package", POTION_ID, PotionRarity.UNCOMMON, PotionSize.SPIKY, PotionColor.ENERGY);
        potency = getPotency();
        description = "Add #ySmite, #ySafety and a #yShiv into your hand. Heal #b" + potency + " HP.";
        labOutlineColor = FishingMod.characterColor;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = "Add #ySmite, #ySafety and a #yShiv into your hand. Heal #b" + potency + " HP.";
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        makeInHand(new Smite());
        makeInHand(new Safety());
        makeInHand(new Shiv());
        atb(new HealAction(AbstractDungeon.player, AbstractDungeon.player, potency));
    }

    @Override
    public int getPotency(int ascensionlevel) {
        return 6;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new CarePackage();
    }
}
