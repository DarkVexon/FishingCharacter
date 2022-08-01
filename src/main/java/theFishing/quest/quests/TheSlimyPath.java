package theFishing.quest.quests;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import theFishing.cards.treasures.AbstractTreasureCard;
import theFishing.util.Wiz;

public class TheSlimyPath extends AbstractQuest {

    public static final String ID = "TheSlimyPath";

    public TheSlimyPath() {
        super(ID, 3);
    }

    @Override
    public String getName() {
        return "The Slimy Path";
    }

    @Override
    public void grantReward() {
        Wiz.shuffleIn(AbstractTreasureCard.returnRandomLegendaryTreasure());
    }

    @Override
    public String getDescription() {
        return "#yQuest: #yExhaust #b" + goal + " #ySlimed. NL #yReward: Put a #yLegendary #yTreasure on top of your draw pile.";
    }

    @Override
    public void onExhaust(AbstractCard c) {
        if (c.cardID.equals(Slimed.ID)) {
            increment();
        }
    }
}
