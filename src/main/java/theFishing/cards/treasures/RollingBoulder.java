package theFishing.cards.treasures;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class RollingBoulder extends AbstractTreasureCard {
    public final static String ID = makeID("RollingBoulder");
    // intellij stuff skill, none, , , , , 30, 10

    public RollingBoulder() {
        super(ID, -2, CardType.SKILL, CardTarget.NONE);
        baseMagicNumber = magicNumber = 30;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerWhenDrawn() {
        flash();
        addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(22, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        addToTop(new VFXAction(adp(), new ShockWaveEffect(adp().hb.cX, adp().hb.cY, Color.RED.cpy(), ShockWaveEffect.ShockWaveType.NORMAL), 0.3F));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = "this is a huge rock";
        return false;
    }

    public void upp() {
        upgradeMagicNumber(10);
    }
}