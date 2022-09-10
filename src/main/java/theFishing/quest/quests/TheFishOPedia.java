package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import javafx.util.Pair;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.util.TexLoader;

import java.util.ArrayList;

import static theFishing.FishingMod.*;
import static theFishing.util.Wiz.applyToSelf;

public class TheFishOPedia extends AbstractQuest {

    public static String ID = makeID("TheFishOPedia");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    private final ArrayList<Pair<String, String>> alreadyPlayed = new ArrayList<>();

    private static final Texture incomplete = TexLoader.getTexture(makeImagePath("quests/Fishopedia.png"));

    public TheFishOPedia() {
        super(ID, 5);
    }

    @Override
    public String getName() {
        return uiStrings.TEXT[0];
    }

    @Override
    public String getDescription() {
        String returnval = uiStrings.TEXT[1] + goal + uiStrings.TEXT[2];
        if (!alreadyPlayed.isEmpty()) {
            returnval = returnval + uiStrings.TEXT[3];
            for (Pair<String, String> s : alreadyPlayed) {
                returnval = returnval + " NL " + FontHelper.colorString(s.getValue(), "y");
            }
        }
        return returnval;
    }

    @Override
    public void grantReward() {
        applyToSelf(new StrengthPower(AbstractDungeon.player, 3));
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        if (card instanceof AbstractFishCard && !alreadyPlayed.stream().anyMatch(q -> q.getKey().equals(card.cardID))) {
            increment();
            alreadyPlayed.add(new Pair(card.cardID, card.originalName));
        }
    }

    @Override
    public Texture progressTex(int idx) {
        if (progress > idx) {
            return TexLoader.getTexture(makeImagePath("quests/Fishopedia_Completed_" + alreadyPlayed.get(idx).getKey().replaceAll(modID + ":", "") + ".png"));
        }
        return incomplete;
    }
}
