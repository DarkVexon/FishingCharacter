package theFishing.quest.quests;

public class TheSlimyPath extends AbstractTreasureQuest {

    public TheSlimyPath() {
        super(3);
    }

    @Override
    public String getName() {
        return "The Slimy Path";
    }

    @Override
    public String getPreText() {
        return "#yQuest: #yExhaust #b" + goal + " #ySlimed.";
    }
}
