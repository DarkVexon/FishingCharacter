package theFishing.quest.quests;

import theFishing.cards.fish.maelstrom.TheWhale;

import static theFishing.util.Wiz.shuffleIn;

public class TheHarpoon extends AbstractQuest {

    public static final String ID = "TheHarpoon";

    public TheHarpoon() {
        super(ID, 1);
    }

    @Override
    public String getName() {
        return "The Harpoon";
    }

    @Override
    public String getDescription() {
        return "#yQuest: Kill an enemy. NL #yReward: Shuffle #yThe #yWhale into your draw pile.";
    }

    @Override
    public void grantReward() {
        shuffleIn(new TheWhale());
    }

    @Override
    public void onKillEnemy() {
        increment();
    }
}
