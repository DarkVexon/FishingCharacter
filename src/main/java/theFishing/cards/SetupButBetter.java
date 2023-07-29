package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.DiscardPileSetupAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class SetupButBetter extends AbstractFishingCard {
    public final static String ID = makeID("SetupButBetter");
    // intellij stuff skill, self, uncommon, , , , , , 

    public SetupButBetter() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardPileSetupAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}