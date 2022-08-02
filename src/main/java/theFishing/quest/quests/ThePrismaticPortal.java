package theFishing.quest.quests;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.actions.AbandonQuestAction;

import java.util.Arrays;

public class ThePrismaticPortal extends AbstractQuest {
    public static final String ID = "ThePrismaticPortal";

    public boolean[] okayed = new boolean[4];

    public ThePrismaticPortal() {
        super(ID, 4);
    }


    @Override
    public String getName() {
        return "The Prismatic Portal";
    }

    @Override
    public String getDescription() {
        return "#yQuest: Play a Common, #bUncommon, #yRare, and Special card. NL #yReward: Shuffle #yQuest #yTime: #yThe #yGem #ySearch into your draw pile.";
    }

    @Override
    public void grantReward() {

    }

    @Override
    public void onPlayCard(AbstractCard card) {
        int rarity = card.rarity == AbstractCard.CardRarity.COMMON ? 0 : card.rarity == AbstractCard.CardRarity.UNCOMMON ? 1 : card.rarity == AbstractCard.CardRarity.RARE ? 2 : 3;
        if (!okayed[rarity]) {
            okayed[rarity] = true;
            if (okayed[0] && okayed[1] && okayed[2] && okayed[3]) {
                grantReward();
                AbstractDungeon.actionManager.addToBottom(new AbandonQuestAction(this));
            }
        }
    }
}
