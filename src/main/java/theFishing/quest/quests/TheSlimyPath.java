package theFishing.quest.quests;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;

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

    @Override
    public void onExhaust(AbstractCard c) {
        if (c.cardID.equals(Slimed.ID)) {
            increment();
        }
    }
}
