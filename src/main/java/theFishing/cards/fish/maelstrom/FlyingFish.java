package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class FlyingFish extends AbstractFishCard {
    public final static String ID = makeID("FlyingFish");
    // intellij stuff skill, self, , , , , , 

    public FlyingFish() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        exhaust = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Flying Fish", AbstractPower.PowerType.BUFF, true, p, 1) {

            @Override
            public void atEndOfRound() {
                flash();
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, this, 1));
            }

            @Override
            public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
                return damage / 2.0F;
            }

            @Override
            public void updateDescription() {
                description = amount == 1 ? "Receive #b50% less damage this turn." : ("Receive #b50% less damage for the next #b" + amount + " turns.");
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}