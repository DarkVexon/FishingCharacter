package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Jellyfish extends AbstractFishCard {
    public final static String ID = makeID("Jellyfish");
    // intellij stuff skill, enemy, , , , , 4, 2

    public Jellyfish() {
        super(ID, 0, CardType.SKILL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new LambdaPower("Zapped", AbstractPower.PowerType.DEBUFF, false, m, magicNumber) {

            @Override
            public int onAttacked(DamageInfo info, int damageAmount) {
                if (info.type == DamageInfo.DamageType.NORMAL) {
                    flash();
                    addToTop(new DamageAction(owner, new DamageInfo(null, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
                }
                return damageAmount;
            }

            @Override
            public void updateDescription() {
                description = "After receiving attack damage, lose #b" + amount + " HP.";
            }
        });
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}