package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class ComicallyLargeSpoon extends AbstractTreasureCard {
    public final static String ID = makeID("ComicallyLargeSpoon");
    // intellij stuff attack, enemy, 50, 16, , , , 

    public ComicallyLargeSpoon() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 32;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public float getTitleFontSize() {
        return 18F;
    }

    public void upp() {
        upgradeDamage(8);
    }
}