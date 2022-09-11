package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import theFishing.powers.AllOutPower;

import static theFishing.util.Wiz.att;

public class AllOutProcAction extends AbstractGameAction {
    public AllOutProcAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        isDone = true;
        if (AbstractDungeon.player.hand.size() == 0) {
            AbstractDungeon.player.getPower(AllOutPower.ID).flash();
            att(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AttackEffect.FIRE));
            att(new VFXAction(AbstractDungeon.player, new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));
            att(new SFXAction("ATTACK_DEFECT_BEAM"));
        }
    }
}
