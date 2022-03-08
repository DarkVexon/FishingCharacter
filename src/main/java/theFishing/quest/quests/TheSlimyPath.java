package theFishing.quest.quests;

import theFishing.cards.treasures.AbstractTreasureCard;
import theFishing.util.Wiz;

public class TheSlimyPath extends AbstractQuest {

    public TheSlimyPath() {
        super(3);
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
        Wiz.topDeck(AbstractTreasureCard.returnRandomLegendaryTreasure());
    }
}
