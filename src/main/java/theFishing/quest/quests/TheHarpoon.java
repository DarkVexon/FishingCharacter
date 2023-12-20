package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.cards.fish.TheWhale;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.makeInHand;
import static theFishing.util.Wiz.makeInHandTop;

public class TheHarpoon extends AbstractQuest {

    public static final String ID = makeID("TheHarpoon");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public TheHarpoon() {
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
        makeInHand(new TheWhale());
    }

    @Override
    public void grantRewardTop() {
        makeInHandTop(new TheWhale());
    }

    @Override
    public void onKillEnemy() {
        increment();
    }

    private static final Texture incomplete = TexLoader.getTexture(makeImagePath("quests/Harpoon.png"));
    private static final Texture complete = TexLoader.getTexture(makeImagePath("quests/Harpoon_completed.png"));

    @Override
    public Texture progressTex(int idx) {
        if (progress > idx) {
            return complete;
        }
        return incomplete;
    }
}
