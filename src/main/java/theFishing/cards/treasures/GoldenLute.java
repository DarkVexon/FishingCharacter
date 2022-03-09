package theFishing.cards.treasures;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;

public class GoldenLute extends AbstractTreasureCard {
    public final static String ID = makeID("GoldenLute");
    // intellij stuff skill, enemy, rare, , , , , 2, 1

    public GoldenLute() {
        super(ID, 2, CardType.SKILL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        addToBot(new StunMonsterAction(m, p));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}