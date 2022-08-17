package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Swordfish extends AbstractFishCard {
    public final static String ID = makeID("Swordfish");
    // intellij stuff attack, enemy, 7, 2, , , , 

    public Swordfish() {
        super(ID, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeDamage(4);
    }
}