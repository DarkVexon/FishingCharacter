package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
        super(ID, 7);
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

    private static final Texture rare_unc = TexLoader.getTexture(makeImagePath("quests/Portal_Uncommon.png"));
    private static final Texture rare_com = TexLoader.getTexture(makeImagePath("quests/Portal_Uncommon_Completed.png"));

    @Override
    public void onPlayCard(AbstractCard card) {
        increment();
    }

    @Override
    public Texture progressTex(int idx) {
        return progress > idx ? rare_com : rare_unc;
    }

    @Override
    public float textpadding() {
        return 233F;
    }
}
