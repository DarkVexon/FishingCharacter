package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Bobber extends AbstractFishingCard {
    public final static String ID = makeID("Bobber");
    // intellij stuff skill, none, uncommon, 9, 1, 12, 3, , 

    public Bobber() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = "I have to draw this Bobber on my turn to use it.";
        return false;
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}