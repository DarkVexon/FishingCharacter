package theFishing.effects;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisParticle;
import theFishing.util.Wiz;

public class OrbToAllFoesEffect extends AbstractGameEffect {

    public OrbToAllFoesEffect(Color c) {
        this.color = c;
        this.duration = this.startingDuration =  0.8F;
    }

    @Override
    public void update() {
        if (this.duration == this.startingDuration) {
            Wiz.forAllMonstersLiving(m -> {
                AbstractGameEffect g2 = new HemokinesisParticle(AbstractDungeon.player.hb.cX + (MathUtils.random(50, 60) * Settings.scale), AbstractDungeon.player.hb.cY - (MathUtils.random(50, 60) * Settings.scale), m.hb.cX, m.hb.cY, AbstractDungeon.player.flipHorizontal);
                ReflectionHacks.setPrivate(g2, AbstractGameEffect.class, "color", this.color.cpy());
                AbstractDungeon.effectsQueue.add(g2);
            });
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
