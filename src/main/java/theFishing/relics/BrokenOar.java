package theFishing.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;

public class BrokenOar extends AbstractEasyRelic {
    public static final String ID = makeID("BrokenOar");

    public BrokenOar() {
        super(ID, RelicTier.BOSS, LandingSound.HEAVY, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
        AbstractDungeon.player.gameHandSize--;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
        AbstractDungeon.player.gameHandSize++;
    }
}
