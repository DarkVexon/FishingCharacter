package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Boot extends AbstractFishCard {
    public final static String ID = makeID("Boot");
    // intellij stuff skill, none, special, , , , , 1, 1

    public Boot() {
        super(ID, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 1;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeDamage(2);
    }
}