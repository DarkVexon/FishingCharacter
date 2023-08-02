package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.RidiculousFishingPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class RidiculousFishing extends AbstractFishingCard {
    public final static String ID = makeID("RidiculousFishing");
    // intellij stuff power, self, rare, , , , , 4, 1

    public RidiculousFishing() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RidiculousFishingPower(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}