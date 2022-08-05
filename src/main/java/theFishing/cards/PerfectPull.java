package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.PerfectPullAction;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class PerfectPull extends AbstractFishingCard {
    public final static String ID = makeID("PerfectPull");
    // intellij stuff attack, all_enemy, common, 5, 2, , , , 

    public PerfectPull() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 5;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PerfectPullAction(p, multiDamage, AbstractGameAction.AttackEffect.FIRE, false));
    }

    public void upp() {
        upgradeDamage(2);
    }
}