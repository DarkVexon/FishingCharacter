package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Steal extends AbstractFishingCard {
    public final static String ID = makeID("Steal");
    // intellij stuff skill, enemy, special, , , , , 1, 1

    public Steal() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new StrengthPower(m, -magicNumber));
        applyToSelf(new StrengthPower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}