package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EnterTheDungeonAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class KeyToTheCity extends AbstractFishingCard {
    public final static String ID = makeID("KeyToTheCity");
    // intellij stuff attack, all_enemy, rare, 10, 4, , , , 

    public KeyToTheCity() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 11;
        isMultiDamage = true;
        shuffleBackIntoDrawPile = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new EnterTheDungeonAction());
    }

    public void upp() {
        upgradeDamage(4);
    }
}