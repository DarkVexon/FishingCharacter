package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class PreStrike extends AbstractFishingCard {
    public final static String ID = makeID(PreStrike.class.getSimpleName());
    // intellij stuff attack, enemy, common, 12, 4, , , , 

    public PreStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 11;
        cardsToPreview = new Strike();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        makeInHand(new Strike());
    }

    public void upp() {
        upgradeDamage(4);
    }
}