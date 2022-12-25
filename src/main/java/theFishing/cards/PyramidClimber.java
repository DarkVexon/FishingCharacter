package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;
import static theFishing.util.Wiz.topDeck;

public class PyramidClimber extends AbstractFishingCard {
    public final static String ID = makeID("PyramidClimber");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public PyramidClimber() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 13;
        cardsToPreview = new Stone();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        shuffleIn(new Stone());
    }

    public void upp() {
        upgradeBlock(4);
    }
}