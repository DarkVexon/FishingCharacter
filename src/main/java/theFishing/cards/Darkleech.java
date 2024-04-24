package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Darkleech extends AbstractFishingCard {
    public final static String ID = makeID(Darkleech.class.getSimpleName());
    // intellij stuff skill, all_enemy, special, , , , , 7, 2

    public Darkleech() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 1;
        exhaust = true;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AllEnemyLoseHPAction(magicNumber));
        atb(new GainEnergyAction(secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
        uDesc();
    }
}