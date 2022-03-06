package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class SurpriseAttack extends AbstractFishingCard {
    public final static String ID = makeID("SurpriseAttack");
    // intellij stuff attack, enemy, common, 7, 1, , , 2, 1

    public SurpriseAttack() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, isVoyaged() ? AbstractGameAction.AttackEffect.BLUNT_HEAVY : AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (isVoyaged()) {
            applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}