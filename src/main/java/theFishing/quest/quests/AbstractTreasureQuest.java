package theFishing.quest.quests;

import theFishing.cards.treasures.AbstractTreasureCard;
import theFishing.util.Wiz;

public abstract class AbstractTreasureQuest extends AbstractQuest {

    public AbstractTreasureQuest(int goal) {
        super(goal);
    }

    @Override
    public void grantReward() {
        Wiz.topDeck(AbstractTreasureCard.returnRandomLegendaryTreasure());
    }

    @Override
    public String getDescription() {
        return getPreText() + " NL #yReward: Put a #yLegendary #yTreasure on top of your draw pile.";
    }

    public abstract String getPreText();
}
