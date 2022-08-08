package theFishing.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class BoosterBox extends AbstractEasyRelic {
    public static final String ID = makeID("BoosterBox");

    public BoosterBox() {
        super(ID, RelicTier.SHOP, LandingSound.SOLID, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onEquip() {


        AbstractDungeon.combatRewardScreen.open("Foils!");
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
    }
}
