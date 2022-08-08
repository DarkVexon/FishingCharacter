package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.atb;

public class TheTerribleTwos extends AbstractQuest {
    public static final String ID = makeID("TheTerribleTwos");

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public TheTerribleTwos() {
        super(ID, 3);
    }

    @Override
    public String getName() {
        return uiStrings.TEXT[0];
    }

    @Override
    public String getDescription() {
        return uiStrings.TEXT[0] + goal + uiStrings.TEXT[1];
    }

    @Override
    public void grantReward() {
        atb(new GainBlockAction(AbstractDungeon.player, 12));
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        if (card.costForTurn == 2) {
            increment();
        }
    }

    private static Texture incomplete = TexLoader.getTexture(makeImagePath("quests/Twos.png"));
    private static Texture complete = TexLoader.getTexture(makeImagePath("quests/Twos_Completed.png"));

    @Override
    public Texture progressTex(int idx) {
        if (progress > idx) {
            return complete;
        }
        return incomplete;
    }

    @Override
    public float textpadding() {
        return 220F;
    }
}
