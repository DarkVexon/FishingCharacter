package theFishing.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Wander extends AbstractFishingCard {
    public final static String ID = makeID("Wander");
    // intellij stuff attack, all_enemy, common, 9, 1, , , 1, 1

    public Wander() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.LIGHTNING));
        atb(new DiscardAction(p, p, BaseMod.MAX_HAND_SIZE, true));
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
        uDesc();
    }
}