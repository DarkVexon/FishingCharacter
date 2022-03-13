package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class DefinitelyUntrappedIdol extends AbstractTreasureCard {
    public final static String ID = makeID("DefinitelyUntrappedIdol");
    // intellij stuff skill, self, , , , , 50, 10

    public DefinitelyUntrappedIdol() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 50;
        cardsToPreview = new RollingBoulder();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainGoldAction(magicNumber));
        AbstractCard q = new RollingBoulder();
        if (upgraded) q.upgrade();
        shuffleIn(q);
    }

    public void upp() {
        upgradeMagicNumber(10);
        AbstractCard q = new RollingBoulder();
        q.upgrade();
        cardsToPreview = q;
    }
}