package theFishing.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RainbowSmokingEmber extends AbstractGameEffect {
    private float x;
    private final float vX;
    private float y;
    private float vY;
    private final float gravity;
    private static final float INTERVAL = 0.01F;
    private float smokeTimer = 0.0F;

    public RainbowSmokingEmber(float x, float y) {
        this.x = x;
        this.y = y;
        this.vX = MathUtils.random(-600.0F, 600.0F) * Settings.scale;
        this.vY = MathUtils.random(-200.0F, 600.0F) * Settings.scale;
        this.gravity = 800.0F * Settings.scale;
        this.scale = MathUtils.random(0.2F, 0.4F) * Settings.scale;
        this.duration = MathUtils.random(0.3F, 0.4F);
        this.color = new Color(MathUtils.random(0F, 1F), MathUtils.random(0F, 1F), MathUtils.random(0F, 1F), 1F);
    }

    public void update() {
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        this.vY -= this.gravity * Gdx.graphics.getDeltaTime();
        this.smokeTimer -= Gdx.graphics.getDeltaTime();
        if (this.smokeTimer < 0.0F) {
            this.smokeTimer = 0.01F;
            AbstractDungeon.effectsQueue.add(new RainbowSmokeParticle(this.x, this.y, this.color));
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
