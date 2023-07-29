package theFishing.relics;

import theFishing.TheFishing;
import theFishing.actions.AcceptQuestAction;
import theFishing.quest.quests.TheFishOPedia;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class FishOPedia extends AbstractAdventurerRelic {
    public static final String ID = makeID(FishOPedia.class.getSimpleName());

    public FishOPedia() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStart() {
        atb(new AcceptQuestAction(new TheFishOPedia()));
    }
}
