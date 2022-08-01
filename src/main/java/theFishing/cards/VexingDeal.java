package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;
import theFishing.powers.VexingDealPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class VexingDeal extends AbstractFishingCard {
    public final static String ID = makeID("VexingDeal");
    // intellij stuff power, self, rare, , , , , 33, 11

    public VexingDeal() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 17;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VexingDealPower(magicNumber, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}