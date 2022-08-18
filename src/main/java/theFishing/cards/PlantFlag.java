package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.topDeck;

public class PlantFlag extends AbstractFishingCard {
    public final static String ID = makeID("PlantFlag");
    // intellij stuff skill, self, common, , , , , , 

    public PlantFlag() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        AbstractCard q = new Flag();
        makeFoil(q);
        cardsToPreview = q;
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractCard q = new Flag();
        makeFoil(q);
        topDeck(q);
    }

    public void upp() {
        upgradeBlock(3);
    }
}