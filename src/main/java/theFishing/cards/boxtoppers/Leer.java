package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Leer extends AbstractBoxTopper {
    public final static String ID = makeID("Leer");
    // intellij stuff attack, enemy, 4, , , , 3, 1

    public Leer() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RemoveAllBlockAction(m, p));
        for (int i = 0; i < 3; i++) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        }
        applyToEnemy(m, new VulnerablePower(m, secondMagic, false));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}