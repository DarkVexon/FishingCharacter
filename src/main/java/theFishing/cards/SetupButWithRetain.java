package theFishing.cards;

import com.megacrit.cardcrawl.actions.unique.SetupAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class SetupButWithRetain extends AbstractFishingCard {
    public final static String ID = makeID("SetupButWithRetain");
    // intellij stuff skill, self, uncommon, , , , , , 

    public SetupButWithRetain() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SetupAction());
    }

    @Override
    public float getTitleFontSize() {
        return 18F;
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}