package theFishing.patch.foil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theFishing.FishingMod;
import theFishing.util.ImageHelper;

import java.nio.charset.StandardCharsets;

public class FoilShiny {
    @SpirePatch(clz = AbstractCard.class, method = "render", paramtypez = SpriteBatch.class)
    public static class FoilCardsShine {
        private static final ShaderProgram VEX = new ShaderProgram(SpriteBatch.createDefaultShader().getVertexShaderSource(), Gdx.files.internal("fishingResources/shaders/vex.frag").readString(String.valueOf(StandardCharsets.UTF_8)));

        private static FrameBuffer fbo = ImageHelper.createBuffer();
        private static FrameBuffer fbo2 = ImageHelper.createBuffer();
        //private static SpriteBatch sb = new SpriteBatch();
        //private static SpriteBatch sb2 = new SpriteBatch();

        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractCard __instance, SpriteBatch spriteBatch) {
            ShaderProgram vex = VEX;
            if (!Settings.hideCards) {
                if (FoilPatches.isFoil(__instance)) {
                    spriteBatch.end();
                    ImageHelper.beginBuffer(fbo);

                    spriteBatch.begin();
                    __instance.render(spriteBatch, false);
                    spriteBatch.end();
                    fbo.end();

                    ImageHelper.beginBuffer(fbo2);
                    spriteBatch.begin();
                    TextureRegion t = ImageHelper.getBufferTexture(fbo);
                    ShaderProgram oldShader = spriteBatch.getShader();
                    spriteBatch.setShader(vex);
                    vex.setUniformf("x_time", FishingMod.time);
                    spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_DST_COLOR);
                    spriteBatch.draw(t, -Settings.VERT_LETTERBOX_AMT, -Settings.HORIZ_LETTERBOX_AMT);
                    spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                    spriteBatch.end();
                    spriteBatch.setShader(oldShader);

                    fbo2.end();

                    spriteBatch.begin();
                    TextureRegion t2 = ImageHelper.getBufferTexture(fbo2);
                    spriteBatch.draw(t2, -Settings.VERT_LETTERBOX_AMT, -Settings.HORIZ_LETTERBOX_AMT);
                    spriteBatch.end();

                    spriteBatch.begin();
                } else {
                    __instance.render(spriteBatch, false);
                }
            }
            return SpireReturn.Return();
        }
    }
}
