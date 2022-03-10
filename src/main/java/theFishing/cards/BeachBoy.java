package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.SnaggedPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;

public class BeachBoy extends AbstractFishingCard {
    public final static String ID = makeID("BeachBoy");
    // intellij stuff attack, enemy, attack, 5, 1, , , 5, 1

    public BeachBoy() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 5;
        isInnate = true;
        selfRetain = true;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToEnemy(m, new SnaggedPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}