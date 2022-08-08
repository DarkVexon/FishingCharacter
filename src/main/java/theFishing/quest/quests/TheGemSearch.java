package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.cards.TheEternityGem;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.shuffleIn;

public class TheGemSearch extends AbstractQuest {

    public static String ID = makeID("TheGemSearch");

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public TheGemSearch() {
        super(ID, 1);
    }


    @Override
    public String getName() {
        return uiStrings.TEXT[0];
    }

    @Override
    public String getDescription() {
        return uiStrings.TEXT[1];
    }

    @Override
    public void grantReward() {
        shuffleIn(new TheEternityGem());
    }

    private static Texture incomplete = TexLoader.getTexture(makeImagePath("quests/GemSearch.png"));
    private static Texture complete = TexLoader.getTexture(makeImagePath("quests/GemSearch_completed.png"));

    @Override
    public Texture progressTex(int idx) {
        if (progress > idx) {
            return complete;
        }
        return incomplete;
    }

    @Override
    public void atEndOfTurn() {
        if (AbstractDungeon.player.hand.isEmpty()) {
            increment();
        }
    }

    @Override
    public float textpadding() {
        return 180F;
    }
}
