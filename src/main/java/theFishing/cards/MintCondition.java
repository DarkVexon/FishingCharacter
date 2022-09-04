package theFishing.cards;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.MintConditionPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class MintCondition extends AbstractFishingCard {
    public final static String ID = makeID("MintCondition");
    // intellij stuff self, , uncommon, , , , , 2, 1

    public MintCondition() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MintConditionPower(1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}