package theFishing.patch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import javassist.CtBehavior;
import theFishing.FishingMod;
import theFishing.boards.dailies.TowerOfSkies;

import java.lang.reflect.Field;

@SpirePatch(clz = DrawPilePanel.class, method = "render")
public class TowerOfSkiesPatch {
    private static final float HB_W = 300.0F * Settings.scale;
    private static final float HB_H = 420.0F * Settings.scale;

    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(DrawPilePanel __instance, SpriteBatch sb) {
        if (FishingMod.activeBoard == null || !FishingMod.activeBoard.id.equals(TowerOfSkies.ID) || AbstractDungeon.isScreenUp) {
            return;
        }
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            return;
        }
//        if (!AbstractDungeon.player.hand.group.stream().anyMatch(q -> q.hasTag(FishingMod.DELVES))) {
//            return;
//        }

        AbstractCard hovered = null;
        int hoveredIndex = -1;

        AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
        AbstractCard ret = renderCard(__instance, sb, card, 0, 0.45f, true);

        if (ret != null) {
            hovered = ret;
            hoveredIndex = 0;
        }

        if (hovered != null) {
            renderCard(__instance, sb, hovered, hoveredIndex, 0.80f, false);
        }
    }

    private static AbstractCard renderCard(DrawPilePanel __instance, SpriteBatch sb, AbstractCard card, int i, float scale, boolean hitbox) {
        AbstractCard hovered = null;

        // Save card's previous location etc
        float prev_current_x = card.current_x;
        float prev_current_y = card.current_y;
        float prev_drawScale = card.drawScale;
        float prev_angle = card.angle;

        card.current_x = __instance.current_x + (hitbox ? 75 : 245) * Settings.scale;
        card.current_y = __instance.current_y + (220 + (i * 27)) * Settings.scale;
        card.drawScale = scale;
        card.angle = 0;
        card.lighten(true);

        if (hitbox) {
            card.hb.move(card.current_x, card.current_y);
            card.hb.resize(HB_W * card.drawScale, HB_H * card.drawScale);
            card.hb.update();
            if (card.hb.hovered) {
                hovered = card;
            }
        }

        card.render(sb);

        // Reset the card to not mess up other rendering
        card.current_x = prev_current_x;
        card.current_y = prev_current_y;
        card.drawScale = prev_drawScale;
        card.angle = prev_angle;

        return hovered;
    }

    public static class Locator extends SpireInsertLocator {

        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher matcher = new Matcher.MethodCallMatcher(Hitbox.class, "render");
            return LineFinder.findInOrder(ctBehavior, matcher);
        }
    }
}
