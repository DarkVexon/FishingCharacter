package theFishing.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.*;

public class MagicRainbowStarBarrage extends AbstractFishingCard {
    public final static String ID = makeID("MagicRainbowStarBarrage");
    // intellij stuff attack, enemy, uncommon, 2, 1, , , , 

    public MagicRainbowStarBarrage() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 2;
        tags.add(STAR_IN_ART);
    }

    private static Texture tex = TexLoader.getTexture(makeImagePath("ui/star_img.png"));

    public static float scaledRandom(float low, float high) {
        return (MathUtils.random(low, high) * Settings.scale);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 6; i++) {
            float sourceX = p.hb.cX + scaledRandom(50, 60);
            float sourceY = p.hb.cY - scaledRandom(50, 60);
            addToBot(new VFXAction(
                    new VfxBuilder(tex,  sourceX, sourceY, 0.3F)
                            .setColor(new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1))
                            .moveX(sourceX, m.hb.cX)
                            .moveY(sourceY, m.hb.cY)
                            .rotate(MathUtils.random(-300F, 300F))
                            .build()));

            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        }
    }

    public void upp() {
        upgradeDamage(1);
    }
}