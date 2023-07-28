package theFishing.relics;

import theFishing.TheFishing;
import theFishing.actions.AcceptQuestAction;
import theFishing.quest.quests.TheFishOPedia;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class MonsterManual extends AbstractAdventurerRelic {
    public static final String ID = makeID(MonsterManual.class.getSimpleName());

    public MonsterManual() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStart() {
        makeInHand(new PartyMember());
    }
}
