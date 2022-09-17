package theFishing.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RainbowSmokeParticle extends AbstractGameEffect {
    private final float x;
    private final float y;
    private float vX;
    private float scale = 0.01F;
    private final float targetScale;
    private static TextureAtlas.AtlasRegion img;

    public RainbowSmokeParticle(float x, float y, Color color) {
        if (img == null) {
            img = ImageMaster.EXHAUST_L;
        }

        this.targetScale = MathUtils.random(0.3F, 0.6F) * Settings.scale;
        this.color = color;
        this.x = x - (float) img.packedWidth / 2.0F;
        this.y = y - (float) img.packedHeight / 2.0F;
        this.rotation = MathUtils.random(360.0F);
        this.duration = 0.3F;
    }

    public void update() {
        this.scale = Interpolation.swingIn.apply(this.targetScale, 0.1F, this.duration / 0.6F);
        this.rotation += this.vX * 2.0F * Gdx.graphics.getDeltaTime();
        this.color.a = this.duration;
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, (float) img.packedWidth / 2.0F, (float) img.packedHeight / 2.0F, (float) img.packedWidth, (float) img.packedHeight, this.scale, this.scale, this.rotation);
    }

    public void dispose() {
    }
}
