package theFishing.relics;

import theFishing.TheFishing;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;

public class TheRod extends AbstractEasyRelic {
    public static final String ID = makeID("TheRod");

    public TheRod() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        for (int i = 0; i < 2; i++) {
            shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }
}
