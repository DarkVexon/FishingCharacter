package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class GazeOfTheGods extends AbstractFishingCard {
    public final static String ID = makeID("GazeOfTheGods");
    // intellij stuff skill, enemy, rare, , , , , 30, 10

    public GazeOfTheGods() {
        super(ID, 4, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 30;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new LoseHPAction(m, p, magicNumber));
    }

    @Override
    public void triggerOnExhaust() {
        atb(new ReduceCostAction(this));
        atb(new ExhaustToHandAction(this));
    }

    public void upp() {
        upgradeMagicNumber(10);
    }
}