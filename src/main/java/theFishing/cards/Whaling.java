package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.actions.WhalingShuffleInAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Whaling extends AbstractFishingCard {
    public final static String ID = makeID("Whaling");
    // intellij stuff power, self, uncommon, , , , , , 

    public Whaling(boolean depth) {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        if (!depth) {
            AbstractCard q = new Whaling(true);
            q.cost += 1;
            q.costForTurn = q.cost;
            q.baseMagicNumber += 1;
            q.magicNumber = q.baseMagicNumber;
            cardsToPreview = q;
        }
    }

    public Whaling() {
        this(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        atb(new WhalingShuffleInAction(this, 1, false, true));
    }

    public void upp() {
        selfRetain = true;
        uDesc();
        if (cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
    }
}