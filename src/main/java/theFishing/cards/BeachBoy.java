package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class BeachBoy extends AbstractFishingCard {
    public final static String ID = makeID("BeachBoy");
    // intellij stuff attack, enemy, attack, 5, 1, , , 5, 1

    public BeachBoy() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 6;
        isInnate = true;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);

    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}