package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class RatedX extends AbstractFishingCard {
    public final static String ID = makeID("RatedX");
    // intellij stuff attack, enemy, rare, 9, 3, 14, 4, 1, 1

    public RatedX() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
        baseBlock = 14;
        baseMagicNumber = magicNumber = 1;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (effect >= 4) {
                att(new GainBlockAction(p, block));
            }
            if (effect >= 2) {
                applyToSelfTop(new StrengthPower(p, magicNumber));
            }
            if (effect >= 1) {
                applyToEnemyTop(m, new VulnerablePower(m, magicNumber, false));
                applyToEnemyTop(m, new WeakPower(m, magicNumber, false));
            }
            dmgTop(m, AbstractGameAction.AttackEffect.NONE);

            this.addToTop(new VFXAction(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, -200.0F, 250.0F, 3.0F, Color.VIOLET, Color.PINK)));
            this.addToTop(new SFXAction("ATTACK_FAST", 0.2F));
            this.addToTop(new SFXAction("ATTACK_WHIFF_1", 0.2F));
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(4);
        upgradeMagicNumber(1);
    }
}