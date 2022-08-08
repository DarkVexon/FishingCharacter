package theFishing.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import theFishing.FishingMod;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class OceanInAJar extends AbstractPotion {
    public static final String POTION_ID = makeID("OceanInAJar");

    public OceanInAJar() {
        super("Ocean In A Jar", POTION_ID, PotionRarity.COMMON, PotionSize.BOTTLE, PotionColor.BLUE);
        potency = getPotency();
        description = "Add #b" + potency + " #yfishing:Fish into your hand.";
        labOutlineColor = FishingMod.characterColor;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = "Add #b" + potency + " #yfishing:Fish into your hand.";
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        for (int i = 0; i < potency; i++)
            makeInHand(AbstractFishCard.returnRandomFish());
    }

    @Override
    public int getPotency(int ascensionlevel) {
        return 2;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new OceanInAJar();
    }
}
