package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;

public class BombFishing extends AbstractFishingCard {
    public final static String ID = makeID("BombFishing");
    // intellij stuff skill, self, uncommon, , , , , 3, -1

    public BombFishing() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Surprise();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Surprise();
        if (upgraded) q.upgrade();
        shuffleIn(q);
    }

    public void upp() {
        AbstractCard q = new Surprise();
        q.upgrade();
        cardsToPreview = q;
        uDesc();
    }
}