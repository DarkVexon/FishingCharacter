package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.powers.TerminaEmpoweredPower;
import theFishing.powers.TerminaStackPower;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.att;

public class Termina extends AbstractBoard {
    public static final String ID = FishingMod.makeID(Termina.class.getSimpleName());

    public Termina() {
        super(ID);
    }

    @Override
    public void effect() {
        AbstractPower result = AbstractDungeon.player.getPower(TerminaStackPower.ID);
        if (result == null || (result != null && result.amount == 1)) {
            applyToSelfTop(new TerminaStackPower(1));
            att(new SFXAction("fishing:CLOCKTOWER"));
        } else {
            att(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, result));
            int damage = 20;
            AbstractPower p = AbstractDungeon.player.getPower(TerminaEmpoweredPower.ID);
            if (p != null) {
                damage += p.amount;
            }
            att(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
}