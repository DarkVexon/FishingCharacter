package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import theFishing.cards.TheEternityGem;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.shuffleIn;

public class TheGemSearch extends AbstractQuest {

    public static String ID = makeID("TheGemSearch");

    public TheGemSearch() {
        super(ID, 1);
    }


    @Override
    public String getName() {
        return "The Gem Search";
    }

    @Override
    public String getDescription() {
        return "#yQuest: End your turn with an empty hand. NL #yReward: Shuffle #yThe #yEternity #yGem into your draw pile.";
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
}
