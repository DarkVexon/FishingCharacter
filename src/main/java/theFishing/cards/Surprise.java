package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.adp;

public class Surprise extends AbstractFishingCard {
    public final static String ID = makeID("Surprise");
    // intellij stuff skill, self, special, , , , , , 

    public Surprise() {
        super(ID, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        isEthereal = true;
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
        cantUseMessage = "this is a bomb";
        return false;
    }

    public void upp() {
    }
}