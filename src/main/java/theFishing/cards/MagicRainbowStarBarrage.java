package theFishing.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.*;
import static theFishing.util.StarHelper.isStarCard;
import static theFishing.util.Wiz.*;

public class MagicRainbowStarBarrage extends AbstractFishingCard {
    public final static String ID = makeID("MagicRainbowStarBarrage");
    // intellij stuff attack, enemy, uncommon, 2, 1, , , , 

    public MagicRainbowStarBarrage() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 3;
        tags.add(STAR_IN_ART);
    }

    private static Texture tex = TexLoader.getTexture(makeImagePath("ui/star_img.png"));

    public static float scaledRandom(float low, float high) {
        return (MathUtils.random(low, high) * Settings.scale);
    }

    protected static void fireStar(AbstractPlayer p, AbstractMonster m) {
        float sourceX = p.hb.cX + scaledRandom(50, 60);
        float sourceY = p.hb.cY - scaledRandom(50, 60);
        atb(new VFXAction(
                new VfxBuilder(tex,  sourceX, sourceY, 0.3F)
                        .setColor(new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1))
                        .moveX(sourceX, m.hb.cX)
                        .moveY(sourceY, m.hb.cY)
                        .rotate(MathUtils.random(-300F, 300F))
                        .build()));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        fireStar(p, m);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToSelf(new LambdaPower("Starlit Shots", AbstractPower.PowerType.BUFF, true, p, magicNumber) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (isStarCard(card)) {
                    flash();
                    atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractMonster q = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                            fireStar(p, q);
                            att(new DamageAction(q, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
                        }
                    });
                }
            }

            @Override
            public void atEndOfTurn(boolean isPlayer) {
                atb(new RemoveSpecificPowerAction(owner, owner, this));
            }

            @Override
            public void updateDescription() {
                description = "Whenever you play a card with a star in its art this turn, deal #b" + amount + " damage to a random enemy.";
            }
        });
    }

    @Override
    public float getTitleFontSize() {
        return 18F;
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}