package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.atb;

public class HelloThere extends AbstractBoxTopper {
    public final static String ID = makeID("HelloThere");
    // intellij stuff skill, all_enemy, , , , , 1, 1

    public HelloThere() {
        super(ID, 0, CardType.SKILL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        isInnate = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}