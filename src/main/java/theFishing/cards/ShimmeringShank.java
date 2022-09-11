package theFishing.cards;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.ShinyShivPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class ShimmeringShank extends AbstractFishingCard {
    public final static String ID = makeID("ShimmeringShank");
    // intellij stuff attack, enemy, uncommon, 4, 2, , , , 

    public ShimmeringShank() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
        cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new ShinyShivPower());
    }

    public void upp() {
        upgradeBlock(3);
    }
}