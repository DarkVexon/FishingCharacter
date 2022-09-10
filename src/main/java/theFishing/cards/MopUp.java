package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.MopUpAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class MopUp extends AbstractFishingCard {
    public final static String ID = makeID("MopUp");
    // intellij stuff attack, enemy, common, 7, 3, , , , 

    public MopUp() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new MopUpAction());
    }

    public void upp() {
        upgradeDamage(3);
    }
}
