package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import theFishing.cards.fish.maelstrom.TheWhale;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.shuffleIn;

public class TheHarpoon extends AbstractQuest {

    public static final String ID = "TheHarpoon";

    public TheHarpoon() {
        super(ID, 1);
    }

    @Override
    public String getName() {
        return "The Harpoon";
    }

    @Override
    public String getDescription() {
        return "#yQuest: Kill an enemy. NL #yReward: Shuffle #yThe #yWhale into your draw pile.";
    }

    @Override
    public void grantReward() {
        shuffleIn(new TheWhale());
    }

    @Override
    public void onKillEnemy() {
        increment();
    }

    private static Texture incomplete = TexLoader.getTexture(makeImagePath("quests/Harpoon.png"));
    private static Texture complete = TexLoader.getTexture(makeImagePath("quests/Harpoon_completed.png"));

    @Override
    public Texture progressTex(int idx) {
        if (progress > idx) {
            return complete;
        }
        return incomplete;
    }
}
