package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Glogus extends AbstractFishingCard {
    public final static String ID = makeID("Glogus");
    // intellij stuff attack, enemy, special, 16, 4, , , , 

    public Glogus() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.POISON);
    }

    public void upp() {
        upgradeDamage(5);
    }
}