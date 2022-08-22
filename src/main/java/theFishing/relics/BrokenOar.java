package theFishing.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class BrokenOar extends AbstractEasyRelic {
    public static final String ID = makeID("BrokenOar");

    public BrokenOar() {
        super(ID, RelicTier.BOSS, LandingSound.HEAVY, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
        AbstractDungeon.player.masterHandSize--;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
        AbstractDungeon.player.masterHandSize--;
    }
}
