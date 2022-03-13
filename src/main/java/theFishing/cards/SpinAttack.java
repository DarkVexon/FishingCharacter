package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class SpinAttack extends AbstractFishingCard {
    public final static String ID = makeID("SpinAttack");
    // intellij stuff attack, all_enemy, starter, 7, 3, , , , 

    public SpinAttack() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        isMultiDamage = true;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new DiscardAction(p, p, 1, false));
    }

    public void upp() {
        upgradeDamage(3);
    }
}