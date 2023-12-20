package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.FinishingTouchesPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class FinishingTouches extends AbstractFishingCard {
    public final static String ID = makeID("FinishingTouches");
    // intellij stuff power, self, rare, , , , , , 

    public FinishingTouches() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(p, p, 2, false));
        applyToSelf(new FinishingTouchesPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}