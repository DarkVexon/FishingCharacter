package theFishing.boards.dailies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BobEffect;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.util.ImageHelper;
import theFishing.util.TexLoader;
import theFishing.util.Wiz;

import static theFishing.util.Wiz.att;

public class PowerBar extends AbstractBoard {
    public static final String ID = FishingMod.makeID(PowerBar.class.getSimpleName());

    private static final Texture barTex = TexLoader.getTexture("fishingResources/images/ui/powerbar.png");

    private int power = 0;

    private static final float BAR_X = 250F * Settings.scale;
    private static final float BAR_Y = 500F * Settings.scale;
    private static final float CANNON_SIZE_W = 50F * Settings.scale;
    private static final float CANNON_SIZE_H = 150F * Settings.scale;

    private final Hitbox hb;
    private final BobEffect b = new BobEffect();

    public PowerBar() {
        super(ID);
        for (int i = 0; i < 3; i++) {
            effects.add(() -> {
                powerEffect();
            });
        }
        hb = new Hitbox(BAR_X, BAR_Y, CANNON_SIZE_W, CANNON_SIZE_H);
    }

    @Override
    public void onUnusedBlock(int unusedBlock) {
        power += unusedBlock;
    }

    @Override
    public void atBattleStartPreDraw() {
        power = 0;
    }

    @Override
    public void render(SpriteBatch sb) {
        ImageHelper.drawTextureScaled(sb, barTex, BAR_X, BAR_Y);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelAmountFont, Integer.toString(power), BAR_X + (CANNON_SIZE_W / 2) - 3, BAR_Y, Color.WHITE.cpy());
    }

    @Override
    public void update() {
        b.update();
        hb.update();
        if (hb.hovered) {
            TipHelper.renderGenericTip(BAR_X + CANNON_SIZE_W, BAR_Y + CANNON_SIZE_H, getLocString(ID).TEXT[1], getLocString(ID).TEXT[2] + power);
        }
    }

    private void powerEffect() {
        if (power >= 30) {
            att(new GainEnergyAction(1));
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    power -= 5;
                }
            });
        }
        if (power >= 18) {
            Wiz.applyToSelfTop(new StrengthPower(AbstractDungeon.player, 1));
        }
        if (power >= 10) {
            att(new DrawCardAction(1));
        }
        if (power >= 5) {
            att(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(4, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        }
    }
}
