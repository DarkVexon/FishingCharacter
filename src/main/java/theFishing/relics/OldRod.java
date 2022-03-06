package theFishing.relics;

import theFishing.TheFishing;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;

public class OldRod extends AbstractEasyRelic {
    public static final String ID = makeID("OldRod");

    public OldRod() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }
}
