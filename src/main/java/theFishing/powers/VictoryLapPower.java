package theFishing.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class VictoryLapPower extends AbstractEasyPower implements OnShufflePower {
    public static String ID = makeID(VictoryLapPower.class.getSimpleName());

    public VictoryLapPower(int amount) {
        super("Victory Lap", PowerType.BUFF, false, AbstractDungeon.player, amount);
    }


    @Override
    public void onShuffle() {
        flash();
        atb(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public void updateDescription() {
        description = "Whenever you shuffle your draw pile, deal #b" + amount + " damage to a random enemy.";
    }
}
