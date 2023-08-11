package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.actions.StormCompletionAction;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.atb;

public class TheStorm extends AbstractQuest {

    public static final String ID = makeID("TheStorm");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    private int totalDmg;

    public TheStorm(int totalDmg) {
        super(ID, 6);
        this.totalDmg = totalDmg;
    }

    @Override
    public String getName() {
        return uiStrings.TEXT[0];
    }

    @Override
    public String getDescription() {
        return uiStrings.TEXT[1] + goal + uiStrings.TEXT[2] + totalDmg + uiStrings.TEXT[3];
    }

    @Override
    public void grantReward() {
        atb(new StormCompletionAction(totalDmg));
    }

    @Override
    public void onSpendEnergy(int amount) {
        for (int i = 0; i < amount; i++) {
            increment();
        }
    }

    private static final Texture incomplete = TexLoader.getTexture(makeImagePath("quests/Storm.png"));
    private static final Texture complete = TexLoader.getTexture(makeImagePath("quests/Storm_Completed.png"));

    @Override
    public Texture progressTex(int idx) {
        if (progress > idx) {
            return complete;
        }
        return incomplete;
    }

    @Override
    public void atEndOfTurn() {
        progress = 0;
    }

    @Override
    public float textpadding() {
        return 150F;
    }
}
