package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class Maw extends AbstractFishCard {
    public final static String ID = makeID("Maw");
    // intellij stuff attack, enemy, 4, , , , , 

    public Maw() {
        super(ID, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }
}