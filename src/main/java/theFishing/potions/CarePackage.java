package theFishing.potions;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
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
    private static PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public CarePackage() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.SPIKY, PotionColor.ENERGY);
        potency = getPotency();
        description = potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
        labOutlineColor = FishingMod.characterColor;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
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
