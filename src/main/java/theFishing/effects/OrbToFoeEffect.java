package theFishing.effects;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisParticle;

public class OrbToFoeEffect extends AbstractGameEffect {
    private AbstractMonster target;

    public OrbToFoeEffect(Color c, AbstractMonster target) {
        this.color = c;
        this.duration = this.startingDuration = 0.8F;
        this.target = target;
    }

    @Override
    public void update() {
        if (this.duration == this.startingDuration) {
            AbstractGameEffect g2 = new HemokinesisParticle(AbstractDungeon.player.hb.cX + (MathUtils.random(50, 60) * Settings.scale), AbstractDungeon.player.hb.cY - (MathUtils.random(50, 60) * Settings.scale), target.hb.cX, target.hb.cY, AbstractDungeon.player.flipHorizontal);
            ReflectionHacks.setPrivate(g2, AbstractGameEffect.class, "color", this.color.cpy());
            AbstractDungeon.effectsQueue.add(g2);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (duration <= 0) {
            isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
