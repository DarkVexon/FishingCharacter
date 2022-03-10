package theFishing.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static theFishing.FishingMod.makeID;

public class SnaggedPower extends AbstractEasyPower implements HealthBarRenderPower {
    public static String ID = makeID(SnaggedPower.class.getSimpleName());
    private static final Color barColor = Color.valueOf("29a2ff");

    public SnaggedPower(AbstractCreature target, int amount) {
        super("Snagged", PowerType.DEBUFF, false, target, amount);
    }

    @Override
    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                flashWithoutSound();
                AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(owner, owner, amount, AbstractGameAction.AttackEffect.POISON));
            }
        }
    }

    @Override
    public int getHealthBarAmount() {
        return amount;
    }

    @Override
    public Color getColor() {
        return barColor;
    }

    @Override
    public void updateDescription() {
        description = "At the start of its turn, " + owner.name + " loses #b" + amount + " HP.";
    }
}