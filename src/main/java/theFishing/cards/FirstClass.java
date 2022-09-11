package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.FirstClassPower;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.applyToSelf;

public class FirstClass extends AbstractFishingCard {
    public final static String ID = makeID("FirstClass");
    // intellij stuff power, self, uncommon, , , 6, 2, , 

    public FirstClass() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        AbstractCard q = new Flight();
        makeFoil(q);
        cardsToPreview = q;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FirstClassPower());
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}