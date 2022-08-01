package theFishing.quest.quests;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.actions.RepeatCardAction;
import theFishing.util.StarHelper;

import static theFishing.util.Wiz.att;

public class TheStarrySky extends AbstractQuest {

    public static final String ID = "TheStarrySky";

    public TheStarrySky() {
        super(ID, 4);
    }


    @Override
    public String getName() {
        return "The Starry Sky";
    }

    @Override
    public String getDescription() {
        return "#yQuest: Play #b" + goal + " cards with stars in their art in a row. NL #yReward: Play the last card again.";
    }

    @Override
    public void grantReward() {
        AbstractCard q = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size()-1);
        att(new RepeatCardAction(q));
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        if (StarHelper.isStarCard(card)) {
            increment();
        }
        else {
            progress = 0;
        }
    }
}
