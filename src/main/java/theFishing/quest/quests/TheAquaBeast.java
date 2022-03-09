package theFishing.quest.quests;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.cards.fish.AbstractFishCard;

public class TheAquaBeast extends AbstractTreasureQuest {
    public TheAquaBeast() {
        super(5);
    }


    @Override
    public String getName() {
        return "The Aqua Beast";
    }

    @Override
    public void onCardPlayed(AbstractCard card) {
        if (card instanceof AbstractFishCard) {
            increment();
        }
    }

    @Override
    public String getPreText() {
        return "#yQuest: Play #b" + goal + " #yFish.";
    }
}
