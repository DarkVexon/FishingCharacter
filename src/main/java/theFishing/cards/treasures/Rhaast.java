package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class Rhaast extends AbstractTreasureCard {
    public final static String ID = makeID("Rhaast");
    // intellij stuff attack, enemy, 10, 3, , , , 

    public Rhaast() {
        super(ID, 1, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 13;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}