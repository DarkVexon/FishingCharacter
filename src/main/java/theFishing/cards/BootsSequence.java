package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theFishing.cards.fish.Boot;
import theFishing.cards.fish.Octopus;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class BootsSequence extends AbstractFishingCard {
    public final static String ID = makeID("BootsSequence");
    // intellij stuff skill, self, uncommon, , , , , 5, 1

    public BootsSequence() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Octopus();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            shuffleIn(new Octopus());
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}