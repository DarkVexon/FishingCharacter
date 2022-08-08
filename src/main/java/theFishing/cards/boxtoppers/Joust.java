package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Joust extends AbstractBoxTopper {
    public final static String ID = makeID("Joust");
    // intellij stuff attack, enemy, 12, 2, , , 1, 1

    public Joust() {
        super(ID, 0, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.group.stream().noneMatch(q -> q != this && q.type == CardType.ATTACK)) {
            return super.canUse(p, m);
        }
        else {
            cantUseMessage = "There are other Attacks in my hand!";
            return false;
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}