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

import java.nio.charset.StandardCharsets;

public class Shiney {
    @SpirePatch(clz = AbstractCard.class, method = "render", paramtypez = SpriteBatch.class)
    public static class Foil {
        private static final ShaderProgram VEX = new ShaderProgram(SpriteBatch.createDefaultShader().getVertexShaderSource(), Gdx.files.internal("fishingResources/shaders/vex.frag").readString(String.valueOf(StandardCharsets.UTF_8)));

        private static FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);

        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractCard __instance, SpriteBatch spriteBatch) {
            ShaderProgram vex = VEX;
            if (!Settings.hideCards) {
                if (FoilPatches.isFoil(__instance)) //Use this to determine whether or not to apply the shader to a card instance
                {
                    spriteBatch.end();
                    fbo.begin();
                    Gdx.gl.glClearColor(0, 0, 0, 0);
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                    spriteBatch.begin();
                    __instance.render(spriteBatch, false);
                    spriteBatch.end();
                    fbo.end();              //Draws the card to the framebuffer

                    spriteBatch.begin();
                    TextureRegion t = new TextureRegion(fbo.getColorBufferTexture());
                    t.flip(false, true);
                    ShaderProgram oldShader = spriteBatch.getShader();
                    spriteBatch.setShader(vex);
                    vex.setUniformf("x_time", Foil.getTime());
                    spriteBatch.draw(t, 0, 0, 0, 0, fbo.getWidth(), fbo.getHeight(), 1f, 1f, 0f);
                    //Draws the framebuffer with the shader applied (so the shader is applied uniformly to the card as its made up of different parts)
                    spriteBatch.end();
                    spriteBatch.setShader(oldShader);
                    spriteBatch.begin();
                } else {
                    __instance.render(spriteBatch, false);
                }
            }
            return SpireReturn.Return();
        }

        private static float time = 0f;

        private static float getTime() {
            time += Gdx.graphics.getDeltaTime();
            return time;
        }
    }
}
