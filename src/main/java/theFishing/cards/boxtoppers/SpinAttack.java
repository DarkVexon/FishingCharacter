package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class SpinAttack extends AbstractBoxTopper {
    public final static String ID = makeID("SpinAttack");
    // intellij stuff attack, all_enemy, 12, 4, , , , 

    public SpinAttack() {
        super(ID, 1, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new DiscardAction(p, p, 1, false));
    }

    public void upp() {
        upgradeDamage(4);
    }
}
