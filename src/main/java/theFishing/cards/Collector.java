package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.MintConditionPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Collector extends AbstractFishingCard {
    public final static String ID = makeID(Collector.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , , 

    public Collector() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MintConditionPower(1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}