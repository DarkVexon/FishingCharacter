package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.FishingMod;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class PlannedObsolescence extends AbstractBoxTopper {
    public final static String ID = makeID("PlannedObsolescence");
    // intellij stuff attack, enemy, 9, 3, , , 3, 1

    public PlannedObsolescence() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 3;
        isEthereal = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        FishingMod.bossPreHpLoss += magicNumber;
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}