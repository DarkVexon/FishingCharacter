package theFishing.quest.quests;

public class TheMachinePrince extends AbstractTreasureQuest{
    public TheMachinePrince() {
        super(2);
    }



    @Override
    public String getName() {
        return "The Machine Prince";
    }

    @Override
    public String getPreText() {
        return "#yQuest: End your turn with excess [E] #b" + goal + " times.";
    }
}
