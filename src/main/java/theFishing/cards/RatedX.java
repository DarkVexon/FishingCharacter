package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class RatedX extends AbstractFishingCard {
    public final static String ID = makeID("RatedX");
    // intellij stuff attack, enemy, rare, 9, 3, 14, 4, 1, 1

    public RatedX() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 7;
        baseBlock = 7;
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (effect >= 4) {
                applyToSelfTop(new StrengthPower(p, secondMagic));
            }
            if (effect >= 2) {
                applyToEnemyTop(m, new VulnerablePower(m, magicNumber, false));
            }
            if (effect >= 1) {
                att(new GainBlockAction(p, block));
            }
            dmgTop(m, AbstractGameAction.AttackEffect.NONE);
            this.addToTop(new VFXAction(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}