package theFishing.relics;

import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class MaelstromAnkh extends AbstractAdventurerRelic {
    public static final String ID = makeID("MaelstromAnkh");

    public MaelstromAnkh() {
        super(ID, RelicTier.SHOP, LandingSound.SOLID, TheFishing.Enums.FISHING_COLOR);
    }
}
