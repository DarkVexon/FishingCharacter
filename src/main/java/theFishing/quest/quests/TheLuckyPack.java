package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.actions.RepeatCardAction;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.att;

public class TheLuckyPack extends AbstractQuest {

    public static final String ID = makeID("TheLuckyPack");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public TheLuckyPack() {
        super(ID, 3);
    }

    @Override
    public String getName() {
        return uiStrings.TEXT[0];
    }

    @Override
    public String getDescription() {
        String result = uiStrings.TEXT[1] + goal + uiStrings.TEXT[2];
        return result;
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
        }
    }

    private static final Texture incomplete = TexLoader.getTexture(makeImagePath("quests/LuckyPack.png"));
    private static final Texture complete = TexLoader.getTexture(makeImagePath("quests/LuckyPack_completed.png"));
    private static final Texture incomplete_last = TexLoader.getTexture(makeImagePath("quests/LuckyPack_Last.png"));
    private static final Texture complete_last = TexLoader.getTexture(makeImagePath("quests/LuckyPack_Last_completed.png"));

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

    @Override
    public float textpadding() {
        return 175F;
    }
}
