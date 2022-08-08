package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Fatigue extends AbstractBoxTopper {
    public final static String ID = makeID("Fatigue");
    // intellij stuff skill, enemy, , , , , , 

    public Fatigue() {
        super(ID, 2, CardType.SKILL, CardTarget.ENEMY);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, 99, false));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}