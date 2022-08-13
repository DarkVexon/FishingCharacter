package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.actions.RepeatCardAction;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.TexLoader;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.att;

public class TheLuckyPack extends AbstractQuest {

    public static final String ID = makeID("TheLuckyPack");

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    private ArrayList<String> alreadyPlayed = new ArrayList<>();

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
        if (!alreadyPlayed.isEmpty()) {
            result = result + uiStrings.TEXT[3];
            for (String s : alreadyPlayed) {
                result = result + " NL " + FontHelper.colorString(s, "y");
            }
        }
        return result;
    }

    @Override
    public void grantReward() {
        AbstractCard q = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1);
        att(new RepeatCardAction(q));
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        if (FoilPatches.isFoil(card) && !alreadyPlayed.contains(card.originalName)) {
            increment();
            alreadyPlayed.add(card.originalName);
        }
    }

    private static Texture incomplete = TexLoader.getTexture(makeImagePath("quests/LuckyPack.png"));
    private static Texture complete = TexLoader.getTexture(makeImagePath("quests/LuckyPack_completed.png"));
    private static Texture incomplete_last = TexLoader.getTexture(makeImagePath("quests/LuckyPack_Last.png"));
    private static Texture complete_last = TexLoader.getTexture(makeImagePath("quests/LuckyPack_Last_completed.png"));

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
