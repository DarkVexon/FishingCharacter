package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class ShipRam extends AbstractFishingCard {
    public final static String ID = makeID("ShipRam");
    // intellij stuff attack, enemy, uncommon, 15, 5, , , , 

    public ShipRam() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
        baseSecondDamage = secondDamage = 26;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isVoyaged()) {
            atb(new DamageAction(m, new DamageInfo(p, secondDamage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        } else {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    public void upp() {
        upgradeDamage(5);
        upgradeSecondDamage(8);
    }
}