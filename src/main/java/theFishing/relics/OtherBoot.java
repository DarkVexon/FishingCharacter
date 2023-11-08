package theFishing.relics;

import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class OtherBoot extends AbstractAdventurerRelic {
    public static final String ID = makeID(OtherBoot.class.getSimpleName());

    public OtherBoot() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        if (blockAmount < 5F) {
            return 5;
        }
        return (int) blockAmount;
    }
}
