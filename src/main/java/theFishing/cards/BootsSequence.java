package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.fish.Boot;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class BootsSequence extends AbstractFishingCard {
    public final static String ID = makeID("BootsSequence");
    // intellij stuff skill, self, uncommon, , , , , 5, 1

    public BootsSequence() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        cardsToPreview = new Boot();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            topDeck(new Boot());
        }
        if (upgraded) {
            applyToSelf(new DrawCardNextTurnPower(p, 1));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}