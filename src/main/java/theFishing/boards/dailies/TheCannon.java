package theFishing.boards.dailies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.util.ImageHelper;
import theFishing.util.TexLoader;

import static theFishing.util.Wiz.*;

public class TheCannon extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheCannon.class.getSimpleName());

    private static final Texture cannonTex = TexLoader.getTexture("fishingResources/images/ui/cannon2.png");

    private int cannonValue = 4;

    private static final float CANNON_X = 750F * Settings.scale;
    private static final float CANNON_Y = 325F * Settings.scale;
    private static final float CANNON_SIZE = 150F * Settings.scale;

    private final Hitbox hb;
    private final BobEffect b = new BobEffect();

    public TheCannon() {
        super(ID);
        effects.add(() -> att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                cannonValue += 8;
            }
        }));
        effects.add(() -> att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                cannonValue += 8;
            }
        }));
        effects.add(() -> att(new GainEnergyAction(2)));
        hb = new Hitbox(CANNON_X, CANNON_Y, CANNON_SIZE, CANNON_SIZE);
    }

    @Override
    public void atBattleStartPreDraw() {
        cannonValue = 4;
    }

    @Override
    public void render(SpriteBatch sb) {
        ImageHelper.drawTextureScaled(sb, cannonTex, CANNON_X, CANNON_Y);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelAmountFont, Integer.toString(cannonValue), CANNON_X + (CANNON_SIZE / 2), CANNON_Y + CANNON_SIZE + b.y, Color.WHITE.cpy());
    }

    @Override
    public void update() {
        b.update();
        hb.update();
        if (hb.hovered) {
            TipHelper.renderGenericTip(CANNON_X + CANNON_SIZE, CANNON_Y + CANNON_SIZE, getLocString(ID).TEXT[1], getLocString(ID).TEXT[2] + cannonValue + getLocString(ID).TEXT[3]);
        }
    }

    @Override
    public void atEndOfTurn() {
        if (EnergyPanel.totalCount > 0) {
            AbstractMonster tar = AbstractDungeon.getRandomMonster();
            atb(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
            vfx(new SmallLaserEffect(CANNON_X + (100 * Settings.scale), CANNON_Y + (100 * Settings.scale), tar.hb.cX, tar.hb.cY), 0.1F);
            atb(new DamageAllEnemiesAction(AbstractDungeon.player, cannonValue, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
