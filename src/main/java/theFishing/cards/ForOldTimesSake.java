package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ForOldTimesSake extends AbstractFishingCard {
    public final static String ID = makeID(ForOldTimesSake.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public ForOldTimesSake() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeBaseCost(1);
    }
}