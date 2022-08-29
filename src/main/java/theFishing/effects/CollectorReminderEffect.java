package theFishing.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CollectorReminderEffect extends AbstractGameEffect {
    private final Texture img = this.getImg();
    private float x;
    private float y;
    private final float oX;
    private float oY;
    private final AbstractCard __instance;
    private final boolean flippedX = MathUtils.randomBoolean();

    public CollectorReminderEffect(AbstractCard __instance, Color color) {
        x = __instance.hb.x;
        y = __instance.hb.y;
        oX = MathUtils.random(__instance.hb.width);
        oY = MathUtils.random(__instance.hb.height);
        this.__instance = __instance;
        this.duration = 0.8F;
        this.color = color;
        this.color.a = 0.0F;
        this.scale = MathUtils.random(0.6F, 0.8F) * Settings.scale;
    }

    private Texture getImg() {
        return MathUtils.randomBoolean() ? ImageMaster.GHOST_ORB_1 : ImageMaster.GHOST_ORB_2;
    }

    public void update() {
        x = __instance.hb.x;
        y = __instance.hb.y;
        oY += 20 * Gdx.graphics.getDeltaTime();

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        this.color.a = this.duration / 1.5F;
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, this.x + oX - 64, this.y + oY - 64, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * __instance.drawScale, this.scale * __instance.drawScale, 0.0F, 0, 0, 128, 128, this.flippedX, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
