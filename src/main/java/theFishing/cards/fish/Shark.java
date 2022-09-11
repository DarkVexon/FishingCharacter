package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;

public class Shark extends AbstractFishCard {
    public final static String ID = makeID("Shark");
    // intellij stuff skill, enemy, special, , , , , 2, 1

    public Shark() {
        super(ID, CardType.ATTACK, AbstractCard.CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseDamage = 3;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }
}