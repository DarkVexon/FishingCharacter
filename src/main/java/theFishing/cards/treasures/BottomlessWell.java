package theFishing.cards.treasures;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class BottomlessWell extends AbstractTreasureCard {
    public final static String ID = makeID("BottomlessWell");
    // intellij stuff skill, self, , , , , 5, 1

    public BottomlessWell() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}