package theFishing.cards;

import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Pinball extends AbstractFishingCard {
    public final static String ID = makeID("Pinball");
    // intellij stuff attack, all_enemy, special, 2, 1, , , 4, 

    public Pinball() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 4;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SwordBoomerangAction(new DamageInfo(p, damage, damageTypeForTurn), magicNumber));
    }

    public void upp() {
        upgradeDamage(1);
    }
}