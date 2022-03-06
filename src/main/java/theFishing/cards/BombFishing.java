package theFishing.cards;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class BombFishing extends AbstractFishingCard {
    public final static String ID = makeID("BombFishing");
    // intellij stuff skill, self, uncommon, , , , , 3, -1

    public BombFishing() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Surprise();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        shuffleIn(new Surprise());
        shuffleIn(new Dazed(), magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(-1);
    }
}