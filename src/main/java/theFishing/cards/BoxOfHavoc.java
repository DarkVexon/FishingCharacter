package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AllEnemyLoseHPAction;
import theFishing.actions.DrawUpToEnergyAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class BoxOfHavoc extends AbstractFishingCard implements StartupCard {
    public final static String ID = makeID("BoxOfHavoc");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 1

    public BoxOfHavoc() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawUpToEnergyAction(magicNumber));
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new AllEnemyLoseHPAction(secondMagic));
        return true;
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }
}