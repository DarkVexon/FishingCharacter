package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class PyramidClimber extends AbstractFishingCard {
    public final static String ID = makeID("PyramidClimber");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public PyramidClimber() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 11;
        cardsToPreview = new Stone();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ExhaustAction(1, false, false, false));
        makeInHand(new Stone());
    }

    public void upp() {
        upgradeBlock(4);
    }
}