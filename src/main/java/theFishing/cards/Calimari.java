package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.basefish.Octopus;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;

public class Calimari extends AbstractFishingCard {
    public final static String ID = makeID("Calimari");
    // intellij stuff skill, self, uncommon, , , , , 5, 1

    public Calimari() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Octopus();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            shuffleIn(new Octopus());
        }
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}