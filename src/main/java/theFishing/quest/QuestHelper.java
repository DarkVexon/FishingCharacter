package theFishing.quest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import theFishing.quest.quests.AbstractQuest;
import theFishing.util.ImageHelper;

import java.util.ArrayList;

public class QuestHelper {
    public static ArrayList<AbstractQuest> quests = new ArrayList<>();
    public static ArrayList<Hitbox> boxes = new ArrayList<>();

    public static final float POSITION_X = 10F * Settings.scale;
    public static final float POSITION_Y = 300F * Settings.scale;

    static {
        for (int i = 0; i < 6; i++) {
            boxes.add(new Hitbox(POSITION_X, Settings.HEIGHT - (POSITION_Y + (i * (50F * Settings.scale))), 200F * Settings.scale, 25F * Settings.scale));
        }
    }

    public static void render(SpriteBatch sb) {
        FontHelper.renderFontLeftTopAligned(
                sb,
                FontHelper.tipHeaderFont,
                "Quest Log",
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
}
