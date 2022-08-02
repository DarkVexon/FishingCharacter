package theFishing.quest.quests;

import theFishing.cards.TheEternityGem;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;

public class TheGemSearch extends AbstractQuest {

    public static String ID = makeID("TheGemSearch");

    public TheGemSearch() {
        super(ID, 1);
    }


    @Override
    public String getName() {
        return "The Gem Search";
    }

    @Override
    public String getDescription() {
        return "#yQuest: End your turn with an empty hand. NL #yReward: Shuffle #yThe #yEternity #yGem into your draw pile.";
    }

    @Override
    public void grantReward() {
        shuffleIn(new TheEternityGem());
    }
}
