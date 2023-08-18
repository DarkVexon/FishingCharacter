package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.FishingMod;
import theFishing.actions.EnterTheDungeonAction;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class PlantFlag extends AbstractFishingCard {
    public final static String ID = makeID("PlantFlag");
    // intellij stuff skill, self, common, , , , , , 

    public PlantFlag() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        AbstractCard q = new Flag();
        makeFoil(q);
        cardsToPreview = q;
        tags.add(FishingMod.DELVES);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EnterTheDungeonAction());
        AbstractCard q = new Flag();
        if (upgraded) {
            q.upgrade();
        }
        makeFoil(q);
        makeInHand(q);
    }

    public void upp() {
        AbstractCard q = new Flag();
        q.upgrade();
        makeFoil(q);
        cardsToPreview = q;
        uDesc();
    }
}