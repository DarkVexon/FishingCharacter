package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.ReservesPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Reserves extends AbstractFishingCard {
    public final static String ID = makeID("Reserves");
    // intellij stuff power, self, uncommon, , , , , , 

    public Reserves() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ReservesPower());
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}