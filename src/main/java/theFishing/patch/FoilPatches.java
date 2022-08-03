package theFishing.patch;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class FoilPatches {
    private static final String partialHueRodrigues =
            "vec3 applyHue(vec3 rgb, float hue)\n" +
                    "{\n" +
                    "    vec3 k = vec3(0.57735);\n" +
                    "    float c = cos(hue);\n" +
                    "    //Rodrigues' rotation formula\n" +
                    "    return rgb * c + cross(k, rgb) * sin(hue) + k * dot(k, rgb) * (1.0 - c);\n" +
                    "}\n";
    private static final String vertexShaderHSLC = "attribute vec4 a_position;\n"
            + "attribute vec4 a_color;\n"
            + "attribute vec2 a_texCoord0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "varying float v_lightFix;\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "   v_color = a_color;\n"
            + "   v_texCoords = a_texCoord0;\n"
            + "   v_color.a = pow(v_color.a * (255.0/254.0) + 0.5, 1.709);\n"
            + "   v_lightFix = 1.0 + pow(v_color.a, 1.41421356);\n"
            + "   gl_Position =  u_projTrans * a_position;\n"
            + "}\n";

    private static final String fragmentShaderHSLC =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying float v_lightFix;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialHueRodrigues +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = 6.2831853 * (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt.rgb = applyHue(tgt.rgb, hue);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (0.5 * pow(dot(tgt.rgb, vec3(0.375, 0.5, 0.125)), v_color.w) * v_lightFix + brightness),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";

    private static ShaderProgram shade = new ShaderProgram(vertexShaderHSLC, fragmentShaderHSLC);

    public static boolean isFoil(AbstractCard card) {
        return FoilField.foil.get(card);
    }

    public static void makeFoil(AbstractCard card) {
        FoilField.foil.set(card, true);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class FoilField {
        public static SpireField<Boolean> foil = new SpireField<>(() -> false);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderCardBg"
    )
    public static class FoilShiny {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static void Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (isFoil(__instance)) {
                oldShader = sb.getShader();
                sb.setShader(shade);
                oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", new Color(0.5F, 0.6F, 0.7F, 0.55F));
            }
        }

        public static void Postfix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (isFoil(__instance)) {
                sb.setShader(oldShader);
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", oldColor);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderPortrait"
    )
    public static class FoilSpecialArt {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static void Prefix(AbstractCard __instance, SpriteBatch sb) {
            if (isFoil(__instance)) {
                oldShader = sb.getShader();
                sb.setShader(shade);
                oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", new Color(0.6F, 0.6F, 0.5F, 0.6F));
            }
        }

        public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
            if (isFoil(__instance)) {
                sb.setShader(oldShader);
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", oldColor);
            }
        }
    }
}
