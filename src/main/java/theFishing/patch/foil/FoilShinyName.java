package theFishing.patch.foil;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class FoilShinyName {

    public static boolean CHANGE_OUTPUT = false;

    @SpirePatch(clz = AbstractCard.class, method = "renderTitle")
    public static class FoilCardsShinyName {
        public static void Prefix(AbstractCard __instance) {
            if (FoilPatches.isFoil(__instance))
                CHANGE_OUTPUT = true;
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderTitle")
    public static class FoilCardsShinyNameEnd {
        public static void Postfix(AbstractCard __instance) {
            CHANGE_OUTPUT = false;
        }
    }

    @SpirePatch(clz = FontHelper.class, method = "renderRotatedText")
    public static class AddThatStar {
        public static void Prefix(SpriteBatch sb, BitmapFont font, @ByRef String[] msg, float x, float y, float offsetX, float offsetY, float angle, boolean roundY, Color c) {
            if (CHANGE_OUTPUT) {
                msg[0] = "[#fbf236]" + msg[0].charAt(0) + "[]" + msg[0].substring(1, msg[0].length());
            }
        }
    }
}
