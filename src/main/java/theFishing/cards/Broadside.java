package theFishing.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Broadside extends AbstractFishingCard {
    public final static String ID = makeID(Broadside.class.getSimpleName());
    // intellij stuff attack, enemy, common, 18, 6, , , , 

    public Broadside() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 18;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(Wiz.getFrontmostEnemy(), AbstractGameAction.AttackEffect.FIRE);
        atb(new DiscardAction(p, p, BaseMod.MAX_HAND_SIZE, true));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeDamage(6);
    }
}