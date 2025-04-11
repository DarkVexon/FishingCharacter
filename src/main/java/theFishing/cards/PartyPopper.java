package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class PartyPopper extends AbstractFishingCard {
    public final static String ID = makeID(PartyPopper.class.getSimpleName());
    // intellij stuff attack, enemy, common, 15, 5, , , , 

    public PartyPopper() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void upp() {
        upgradeDamage(5);
    }
}