package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.applyToSelf;

public class Steal extends AbstractFishingCard {
    public final static String ID = makeID("Steal");
    // intellij stuff skill, enemy, special, , , , , 1, 1

    public Steal() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(StrengthPower.POWER_ID)) {
            int found = m.getPower(StrengthPower.POWER_ID).amount;
            if (found > 0) {
                int x = Math.min(magicNumber, found);
                applyToEnemy(m, new StrengthPower(m, -x));
                applyToSelf(new StrengthPower(p, x));
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}