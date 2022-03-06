package theFishing.relics;

import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheFishing.Enums.TODO_COLOR);
    }
}
