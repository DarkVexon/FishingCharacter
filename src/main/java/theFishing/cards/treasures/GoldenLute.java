package theFishing.cards.treasures;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;

public class GoldenLute extends AbstractTreasureCard {
    public final static String ID = makeID("GoldenLute");
    // intellij stuff skill, enemy, rare, , , , , 2, 1

    public GoldenLute() {
        super(ID, 1, CardType.SKILL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 10;
        baseSecondMagic = secondMagic = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new PoisonPower(m, p, magicNumber));
        applyToEnemy(m, new StrengthPower(m, -secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
    }
}