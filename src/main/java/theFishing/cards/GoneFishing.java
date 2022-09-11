package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class GoneFishing extends AbstractFishingCard {
    public final static String ID = makeID("GoneFishing");
    // intellij stuff skill, self, rare, , , , , 3, 2

    public GoneFishing() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IntangiblePlayerPower(p, 1));
        for (int i = 0; i < magicNumber; i++) {
            topDeck(AbstractFishCard.returnRandomFish());
        }
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}