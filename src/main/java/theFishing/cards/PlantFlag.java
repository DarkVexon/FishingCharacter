package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.topDeck;

public class PlantFlag extends AbstractFishingCard {
    public final static String ID = makeID("PlantFlag");
    // intellij stuff skill, self, common, , , , , , 

    public PlantFlag() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        cardsToPreview = new Flag();
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        topDeck(new Flag());
    }

    public void upp() {
        upgradeBlock(3);
    }
}