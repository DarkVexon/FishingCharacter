package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.ShinySeekerPower;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.shuffleIn;

public class Whaling extends AbstractFishingCard {
    public final static String ID = makeID("Whaling");
    // intellij stuff power, self, uncommon, , , , , , 

    public Whaling() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Gem();
        makeFoil(cardsToPreview);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Gem();
        if (upgraded) q.upgrade();
        makeFoil(q);
        shuffleIn(q);
        applyToSelf(new ShinySeekerPower(1));
    }

    public void upp() {
        uDesc();
        if (cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
    }
}