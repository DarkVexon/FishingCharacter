package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.TripwirePower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Tripwire extends AbstractFishingCard {
    public final static String ID = makeID("Tripwire");
    // intellij stuff attack, enemy, uncommon, 6, 1, , , 9, 3

    public Tripwire() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToSelf(new TripwirePower(magicNumber));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(2);
    }
}