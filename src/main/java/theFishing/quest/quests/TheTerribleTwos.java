package theFishing.quest.quests;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.util.Wiz.atb;

public class TheTerribleTwos extends AbstractQuest {
    public static final String ID = "TheTerribleTwos";

    public TheTerribleTwos() {
        super(ID, 3);
    }

    @Override
    public String getName() {
        return "The Terrible Twos";
    }

    @Override
    public String getDescription() {
        return "#yQuest: Play #b" + goal + " 2-cost cards. NL #yReward: Gain #b22 #yBlock.";
    }

    @Override
    public void grantReward() {
        atb(new GainBlockAction(AbstractDungeon.player, 22));
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        if (card.costForTurn == 2) {
            increment();
        }
    }
}
