package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class StarShard extends AbstractFishingCard {
    public final static String ID = makeID("StarShard");
    // intellij stuff attack, enemy, special, 3, 2, 3, 2, , 

    public StarShard() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 3;
        baseBlock = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}