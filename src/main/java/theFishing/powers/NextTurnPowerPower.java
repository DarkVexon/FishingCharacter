package theFishing.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NextTurnPowerPower extends AbstractEasyPower {
    private AbstractPower powerToGain;

    public NextTurnPowerPower(AbstractCreature owner, AbstractPower powerToGrant) {
        super("Next Turn " + powerToGrant.name, powerToGrant.type, true, owner, powerToGrant.amount);
        this.img = powerToGrant.img;
        this.region48 = powerToGrant.region48;
        this.region128 = powerToGrant.region128;
        this.powerToGain = powerToGrant;
        updateDescription();
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.GREEN.cpy());
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        powerToGain.amount += stackAmount;
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, powerToGain, powerToGain.amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    @Override
    public void updateDescription() {
        if (powerToGain == null) {
            description = "???";
        } else {
            description = "Next turn, gain #b" + powerToGain.amount + " " + powerToGain.name + ".";
        }
    }
}
