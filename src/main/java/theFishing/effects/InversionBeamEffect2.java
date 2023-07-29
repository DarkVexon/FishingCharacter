package theFishing.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class InversionBeamEffect2 extends AbstractGameEffect {
    private float x;
    private float y;

    public InversionBeamEffect2(float x) {
        this.startingDuration = 0.5F;
        this.duration = this.startingDuration;
        this.x = x;
        this.y = 0.01F;
        this.renderBehind = false;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        } else if (this.duration < this.startingDuration / 2.0F) {
            this.y = Interpolation.fade.apply(0.01F, 50.0F, this.duration / (this.startingDuration / 2.0F)) * Settings.scale;
        } else {
            this.y = Interpolation.fade.apply(50.0F, 0.01F, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F)) * Settings.scale;
        }

        this.scale = Interpolation.bounce.apply(0.01F, 5.0F, this.duration / this.startingDuration);
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(775, 769);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.x - this.y / 2.0F, 0.0F, this.y, (float) Settings.HEIGHT - 64.0F * Settings.scale);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
