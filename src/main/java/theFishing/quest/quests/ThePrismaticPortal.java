package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.cards.QuestTimeTheGemSearch;
import theFishing.util.TexLoader;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;

public class ThePrismaticPortal extends AbstractQuest {
    public static final String ID = makeID("ThePrismaticPortal");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public ThePrismaticPortal() {
        super(ID, 13);
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
        Wiz.shuffleIn(new QuestTimeTheGemSearch());
    }

    @Override
    public void grantRewardTop() {
        Wiz.shuffleInTop(new QuestTimeTheGemSearch());
    }

    private static final Texture left_unc = TexLoader.getTexture(makeImagePath("quests/Block_Left.png"));
    private static final Texture left_com = TexLoader.getTexture(makeImagePath("quests/Block_Left_Completed.png"));
    private static final Texture mid_unc = TexLoader.getTexture(makeImagePath("quests/Block_Middle.png"));
    private static final Texture mid_com = TexLoader.getTexture(makeImagePath("quests/Block_Middle_Completed.png"));
    private static final Texture right_unc = TexLoader.getTexture(makeImagePath("quests/Block_Right.png"));
    private static final Texture right_com = TexLoader.getTexture(makeImagePath("quests/Block_Right_Completed.png"));

    @Override
    public void onGainBlock(int blockAmount) {
        for (int i = 0; i < blockAmount; i++) {
            increment();
        }
    }

    @Override
    public Texture progressTex(int idx) {
        if (idx == 0) {
            return progress > idx ? left_com : left_unc;
        } else if (idx == goal - 1) {
            return progress > idx ? right_com : right_unc;
        } else {
            return progress > idx ? mid_com : mid_unc;
        }
    }

    @Override
    public float textpadding() {
        return 233F;
    }

    @Override
    public float getImgSpacing() {
        return 16;
    }
}
