package theFishing.quest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.quest.quests.AbstractQuest;
import theFishing.quest.quests.TheGemSearch;
import theFishing.util.ImageHelper;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;

public class QuestHelper {
    public static ArrayList<AbstractQuest> quests = new ArrayList<>();
    public static ArrayList<Hitbox> boxes = new ArrayList<>();

    public static final float POSITION_X = 10F * Settings.scale;
    public static final float POSITION_Y = 300F * Settings.scale;

    public static final Color QUEST_DUPE_BORDER_GLOW_COLOR = new Color(0.2F, 1F, 0.6F, 0.25F);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("QuestLog"));

    static {
        for (int i = 0; i < 6; i++) {
            boxes.add(new Hitbox(POSITION_X, Settings.HEIGHT - (POSITION_Y + (i * (50F * Settings.scale))), 200F * Settings.scale, 27.5F * Settings.scale));
        }
    }

    public static void render(SpriteBatch sb) {
        FontHelper.renderFontLeftTopAligned(
                sb,
                FontHelper.tipHeaderFont,
                uiStrings.TEXT[0],
                POSITION_X,
                Settings.HEIGHT - POSITION_Y + (50 * Settings.scale),
                Settings.GOLD_COLOR
        );
        int xr = 0;

        for (AbstractQuest q : quests) {
            FontHelper.renderFontLeft(
                    sb,
                    FontHelper.tipHeaderFont,
                    q.getName(),
                    boxes.get(xr).x,
                    boxes.get(xr).y + 12.5F,
                    Color.WHITE.cpy()
            );
            for (int i = 0; i < q.goal; i++) {
                ImageHelper.drawTextureScaled(sb, q.progressTex(i), boxes.get(xr).x + (((i * 40) + q.textpadding()) * Settings.scale), boxes.get(xr).y - 3);
            }
            xr++;
        }

        for (Hitbox h : boxes) {
            h.render(sb);
        }
    }

    public static void acceptQuest(AbstractQuest quest) {
        quests.add(quest);
        playAcceptQuestSfx();
        Hitbox box = boxes.get(quests.indexOf(quest));
        float width = quest.textpadding() * Settings.scale + ((quest.goal * 40) * Settings.scale);
        if (quest.questID.equals(TheGemSearch.ID)) width += (40 * Settings.scale);
        box.resize(width, box.height);
    }

    public static void playAcceptQuestSfx() {
        int roll = MathUtils.random(0, 2);
        if (roll == 0) {
            CardCrawlGame.sound.play("BUFF_1");
        } else if (roll == 1) {
            CardCrawlGame.sound.play("BUFF_2");
        } else {
            CardCrawlGame.sound.play("BUFF_3");
        }
    }

    public static void playCompleteQuestSfx() {
        CardCrawlGame.sound.playV("UNLOCK_PING", 0.6F);
    }

    public static void update() {
        for (int i = 0; i < boxes.size(); i++) {
            boxes.get(i).update();
            if (boxes.get(i).hovered) {
                if (i < quests.size()) {
                    ImageHelper.tipBoxAtMousePos(quests.get(i).getName(), quests.get(i).getDescription());
                }
            }
        }
    }

    public static void reset() {
        quests = new ArrayList<>();
    }

    public static void onSpendEnergy(int amount) {
        for (AbstractQuest q : quests) {
            q.onSpendEnergy(amount);
        }
    }

    public static void onCardPlayed(AbstractCard card) {
        for (AbstractQuest q : quests) {
            q.onPlayCard(card);
        }
    }

    public static void onKillEnemy() {
        for (AbstractQuest q : quests) {
            q.onKillEnemy();
        }
    }

    public static void atEndOfTurn() {
        for (AbstractQuest q : quests) {
            q.atEndOfTurn();
        }
    }

    public static boolean hasQuest(String ID) {
        return quests.stream().anyMatch(q -> q.questID.equals(ID));
    }
}
