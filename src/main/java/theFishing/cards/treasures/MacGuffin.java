package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class MacGuffin extends AbstractTreasureCard {
    public final static String ID = makeID("MacGuffin");
    // intellij stuff attack, enemy, 22, 5, 22, 5, 2, 1

    public MacGuffin() {
        super(ID, 2, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 22;
        baseBlock = 22;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(5);
        upgradeBlock(5);
        upgradeMagicNumber(1);
    }
}