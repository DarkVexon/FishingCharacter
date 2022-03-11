package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RepairPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class PastKillingGun extends AbstractTreasureCard {
    public final static String ID = makeID("PastKillingGun");
    // intellij stuff attack, enemy, 18, 6, , , 6, 3

    public PastKillingGun() {
        super(ID, 2, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 18;
        baseMagicNumber = magicNumber = 9;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToSelf(new RepairPower(p, magicNumber));
    }

    public void upp() {
        upgradeDamage(6);
        upgradeMagicNumber(3);
    }
}