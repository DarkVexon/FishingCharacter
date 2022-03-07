package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Cannoneer extends AbstractFishingCard {
    public final static String ID = makeID("Cannoneer");
    // intellij stuff attack, all_enemy, uncommon, 8, 2, , , , 

    public Cannoneer() {
        super(ID, -1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
            }
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(2);
    }
}