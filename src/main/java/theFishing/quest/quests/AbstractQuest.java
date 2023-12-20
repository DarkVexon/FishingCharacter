package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.actions.AbandonQuestAction;
import theFishing.quest.QuestHelper;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.atb;

public abstract class AbstractQuest {
    public int progress;
    public int goal;
    private static final Texture def = TexLoader.getTexture(makeImagePath("quests/default.png"));

    public Texture progressTex(int idx) {
        return def;
    }

    public float textpadding() {
        return 166F;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract void grantReward();

    public abstract void grantRewardTop();

    public String questID;

    public AbstractQuest(String ID, int goal) {
        this.questID = ID;
        this.progress = 0;
        this.goal = goal;
    }

    public void increment() {
        if (progress < goal) {
            progress++;
            if (progress == goal) {
                QuestHelper.playCompleteQuestSfx();
                grantReward();
                atb(new AbandonQuestAction(this));
            }
        }
    }

    public void onSpendEnergy(int amount) {

    }

    public void onPlayCard(AbstractCard card) {

    }

    public void onKillEnemy() {

    }

    public void atEndOfTurn() {

    }

    public void onDrawCard() {

    }

    public float getImgSpacing() {
        return 40F;
    }

    public void onGainBlock(int blockAmount) {

    }
}
