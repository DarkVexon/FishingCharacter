package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.DrawLessNextTurnPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class RodSlam extends AbstractFishingCard {
    public final static String ID = makeID("RodSlam");
    // intellij stuff attack, enemy, 12, 3, , , , 

    public RodSlam() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToSelf(new DrawLessNextTurnPower(1));
    }

    public void upp() {
        upgradeDamage(4);
    }
}