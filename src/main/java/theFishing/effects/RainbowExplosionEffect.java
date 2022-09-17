package theFishing.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RainbowExplosionEffect extends AbstractGameEffect {
    private static final int EMBER_COUNT = 12;
    private final float x;
    private final float y;

    public RainbowExplosionEffect(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        //AbstractDungeon.effectsQueue.add(new DarkSmokePuffEffect(this.x, this.y));

        for (int i = 0; i < 8; ++i) {
            AbstractDungeon.effectsQueue.add(new RainbowSmokingEmber(this.x + MathUtils.random(-50.0F, 50.0F) * Settings.scale, this.y + MathUtils.random(-50.0F, 50.0F) * Settings.scale));
        }

        CardCrawlGame.sound.playA("ATTACK_FIRE", MathUtils.random(-0.2F, -0.1F));
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
