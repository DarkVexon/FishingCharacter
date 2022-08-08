package theFishing.relics;

import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class Newsletter extends AbstractEasyRelic {
    public static final String ID = makeID("Newsletter");

    public Newsletter() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheFishing.Enums.FISHING_COLOR);
    }

    public static final float SHOP_CARD_PRICE_REDUCE = 0.8F; // mult by
}
