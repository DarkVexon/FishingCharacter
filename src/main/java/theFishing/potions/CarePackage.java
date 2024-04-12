package theFishing.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import theFishing.FishingMod;
import theFishing.actions.EnterTheDungeonAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class CarePackage extends AbstractPotion {
    public static final String POTION_ID = makeID("CarePackage");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public CarePackage() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.RARE, PotionSize.SPIKY, PotionColor.ENERGY);
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
        for (int i = 0; i < potency; i++) {
            atb(new EnterTheDungeonAction());
        }
    }

    @Override
    public int getPotency(int ascensionlevel) {
        return 4;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new CarePackage();
    }
}
