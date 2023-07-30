package theFishing.relics;

import theFishing.TheFishing;
import theFishing.actions.EnterTheDungeonAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class MonsterManual extends AbstractAdventurerRelic {
    public static final String ID = makeID(MonsterManual.class.getSimpleName());

    public MonsterManual() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStart() {
        flash();
        atb(new EnterTheDungeonAction());
    }
}
