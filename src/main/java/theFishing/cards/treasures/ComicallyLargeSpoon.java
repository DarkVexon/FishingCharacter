package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ComicallyLargeSpoon extends AbstractTreasureCard {
    public final static String ID = makeID("ComicallyLargeSpoon");
    // intellij stuff attack, enemy, 50, 16, , , , 

    public ComicallyLargeSpoon() {
        super(ID, 3, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 50;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(16);
    }
}