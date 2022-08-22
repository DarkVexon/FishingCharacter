//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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

public class RedPressurePoints extends AbstractGameEffect {
    private float x;
    private float y;
    private final float endX;
    private final float endY;
    private final float scaleMultiplier;
    private final TextureAtlas.AtlasRegion img;

    public RedPressurePoints(float setX, float setY) {
        this.img = ImageMaster.DAGGER_STREAK;
        setX -= 120.0F * Settings.scale;
        setY -= -80.0F * Settings.scale;
        this.endX = setX - (float) this.img.packedWidth / 2.0F;
        this.endY = setY - (float) this.img.packedHeight / 2.0F;
        this.x = this.endX + MathUtils.random(-550.0F, -450.0F) * Settings.scale;
        this.y = this.endY + MathUtils.random(380.0F, 320.0F) * Settings.scale;
        this.startingDuration = 0.4F;
        this.duration = 0.4F;
        this.scaleMultiplier = MathUtils.random(0.1F, 0.2F);
        this.rotation = 150.0F;
        this.color = Color.RED.cpy();
        this.color.a = 0.0F;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.x = Interpolation.swingIn.apply(this.endX, this.x, this.duration * 3.33F);
        this.y = Interpolation.swingIn.apply(this.endY, this.y, this.duration * 3.33F);
        if (this.duration < 0.0F) {
            this.isDone = true;
            this.duration = 0.0F;
        }

        this.color.a = 1.0F - this.duration;
        this.scale = this.duration * Settings.scale + this.scaleMultiplier;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float) this.img.packedWidth / 2.0F, (float) this.img.packedHeight / 2.0F, (float) this.img.packedWidth, (float) this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y, (float) this.img.packedWidth / 2.0F, (float) this.img.packedHeight / 2.0F, (float) this.img.packedWidth, (float) this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
