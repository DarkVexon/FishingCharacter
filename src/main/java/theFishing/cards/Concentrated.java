package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import theFishing.patch.UnnateCoffeePatch;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class Concentrated extends AbstractFishingCard {
    public final static String ID = makeID("Concentrated");
    // intellij stuff power, self, rare, , , , , 2, -1

    public Concentrated() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        UnnateCoffeePatch.UnnateField.unnate.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BerserkPower(p, 1));
    }

    public void upp() {
        UnnateCoffeePatch.UnnateField.unnate.set(this, false);
        uDesc();
    }
}