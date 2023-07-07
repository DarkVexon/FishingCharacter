package theFishing.patch.foil;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.BobEffect;

import java.util.HashMap;

public class FoilShinyName {

    private static final String ADVENTURER_SHINYCOLOR_PREFIX = "[#fbf236]";
    private static final String REMOVE_COLOR = "[]";

    private static HashMap<AbstractCard, Integer> bobMap = new HashMap<>();

    static BobEffect[] bobs = new BobEffect[]{
            new BobEffect(),
            new BobEffect(),
            new BobEffect()
    };

    static {
        bobs[0].speed = 0.3F;
        bobs[1].speed = 0.4F;
        bobs[2].speed = 0.5F;
    }

    static BobEffect[] bobs2 = new BobEffect[]{
            new BobEffect(),
            new BobEffect(),
            new BobEffect()
    };

    public static boolean CHANGE_OUTPUT = false;
    public static float CHANGE_DISTX = 0F;
    public static float CHANGE_DISTY = 0F;

    @SpirePatch(clz = AbstractCard.class, method = "renderTitle")
    public static class FoilCardsShinyName {
        public static void Prefix(AbstractCard __instance) {
            if (FoilPatches.isFoil(__instance)) {
                CHANGE_OUTPUT = true;
                if (!bobMap.containsKey(__instance)) {
                    bobMap.put(__instance, MathUtils.random(0, 2));
                }
                CHANGE_DISTX = bobs2[bobMap.get(__instance)].y;
                CHANGE_DISTY = bobs2[(bobMap.get(__instance) + 1) % 3].y;
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderTitle")
    public static class FoilCardsShinyNameEnd {
        public static void Postfix(AbstractCard __instance) {
            CHANGE_OUTPUT = false;
            CHANGE_DISTX = 0;
            CHANGE_DISTY = 0;
        }
    }

    @SpirePatch(clz = FontHelper.class, method = "renderRotatedText")
    public static class AddThatStar {
        public static void Prefix(SpriteBatch sb, BitmapFont font, @ByRef String[] msg, @ByRef float x[], @ByRef float y[], float offsetX, float offsetY, float angle, boolean roundY, Color c) {
            if (CHANGE_OUTPUT) {
                msg[0] = ADVENTURER_SHINYCOLOR_PREFIX + msg[0] + " GX" + REMOVE_COLOR;
                x[0] += CHANGE_DISTX;
                y[0] += CHANGE_DISTY;
            }
        }
    }

    public static void update() {
        bobs[0].update();
        bobs[1].update();
        bobs[2].update();

        bobs2[0].update();
        bobs2[1].update();
        bobs2[2].update();
    }
}
