package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.actions.RepeatCardAction;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.att;

public class TheLuckyPack extends AbstractQuest {

    public static final String ID = "TheLuckyPack";

    public TheLuckyPack() {
        super(ID, 3);
    }


    @Override
    public String getName() {
        return "The Lucky Pack";
    }

    @Override
    public String getDescription() {
        return "#yQuest: Play #b" + goal + " #yFoil cards in a row. NL #yReward: Play the last card again.";
    }

    @Override
    public void grantReward() {
        AbstractCard q = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1);
        att(new RepeatCardAction(q));
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        if (FoilPatches.isFoil(card)) {
            increment();
        } else {
            progress = 0;
        }
    }

    private static Texture incomplete = TexLoader.getTexture(makeImagePath("quests/LuckyPack.png"));
    private static Texture complete = TexLoader.getTexture(makeImagePath("quests/LuckyPack_completed.png"));
    private static Texture incomplete_last = TexLoader.getTexture(makeImagePath("quests/LuckyPack_Last.png"));
    private static Texture complete_last = TexLoader.getTexture(makeImagePath("quests/LuckyPack_Last_completed.png"));

    @Override
    public Texture progressTex(int idx) {
        if (idx == 2) {
            if (progress > idx) {
                return complete_last;
            }
            return incomplete_last;
        }
        if (progress > idx) {
            return complete;
        }
        return incomplete;
    }
}
