package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.AllOutPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class AllOut extends AbstractFishingCard {
    public final static String ID = makeID("AllOut");
    // intellij stuff power, self, uncommon, , , , , 9, 3

    public AllOut() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(p, p, 1, false));
        applyToSelf(new AllOutPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}