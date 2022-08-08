package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class F extends AbstractBoxTopper {
    public final static String ID = makeID("F");
    // intellij stuff attack, self_and_enemy, 12, 3, 12, 3, , 

    public F() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 12;
        baseBlock = 12;
        cardsToPreview = new Skull();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        shuffleIn(new Skull());
    }

    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }
}