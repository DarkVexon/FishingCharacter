package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.vfx;

public class GazeOfTheGods extends AbstractFishingCard {
    public final static String ID = makeID("GazeOfTheGods");
    // intellij stuff skill, enemy, rare, , , , , 30, 10

    public GazeOfTheGods() {
        super(ID, 4, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 25;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new ThirdEyeEffect(m.hb.cX, m.hb.cY));
        atb(new LoseHPAction(m, p, magicNumber));
    }

    @Override
    public void triggerOnExhaust() {
        atb(new ReduceCostAction(this));
        atb(new ExhaustToHandAction(this));
    }

    public void upp() {
        upgradeMagicNumber(7);
    }
}