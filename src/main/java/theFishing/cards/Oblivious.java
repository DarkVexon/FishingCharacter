package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class Oblivious extends AbstractFishingCard {
    public final static String ID = makeID("Oblivious");
    // intellij stuff skill, self, common, , , 5, 3, , 

    public Oblivious() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        makeInHand(AbstractFishCard.returnRandomFish());
    }

    public void upp() {
        upgradeBlock(3);
    }
}