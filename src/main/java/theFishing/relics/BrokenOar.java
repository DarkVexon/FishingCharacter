package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class BrokenOar extends AbstractAdventurerRelic {
    public static final String ID = makeID(BrokenOar.class.getSimpleName());

    public BrokenOar() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

    @Override
    public void atTurnStartPostDraw() {
        atb(new ExhaustAction(1, false, false, false));
    }
}
