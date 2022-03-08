package theFishing.quest.quests;

public class TheSlimyPath extends AbstractQuest {
    private boolean upgraded;

    public TheSlimyPath(int goal, boolean upgraded) {
        super(goal);
        this.upgraded = upgraded;
    }

    @Override
    public String getName() {
        return "The Slimy Path";
    }

    @Override
    public String getDescription() {
        return "#yQuest: #yExhaust #b" + goal + " #ySlimed. NL #yReward: Put a #yLegendary #yTreasure on top of your draw pile.";
    }

    @Override
    public void grantReward() {

    }
}
