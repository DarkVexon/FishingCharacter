package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.IncreaseCostAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Energize extends AbstractFishingCard {
    public final static String ID = makeID(Energize.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public Energize() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        atb(new IncreaseCostAction(this.uuid));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}