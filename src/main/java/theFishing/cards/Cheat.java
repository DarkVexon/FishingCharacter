package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class Cheat extends AbstractFishingCard {
    public final static String ID = makeID("Cheat");
    // intellij stuff attack, enemy, special, 8, 2, , , , 

    public Cheat() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 9;
        cardsToPreview = new Steal();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.currentBlock > 0) {
            atb(new RemoveAllBlockAction(m, p));
        }
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        AbstractCard q = new Steal();
        if (upgraded) q.upgrade();
        makeInHand(q);
    }

    public void upp() {
        upgradeDamage(2);
        AbstractCard q = new Steal();
        q.upgrade();
        cardsToPreview = q;
        uDesc();
    }
}