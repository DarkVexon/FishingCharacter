package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;


import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.topDeck;

public class PlantFlag extends AbstractFishingCard {
    public final static String ID = makeID("PlantFlag");
    // intellij stuff skill, self, common, , , , , , 

    public PlantFlag() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        cardsToPreview = new Flag();
        baseBlock = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractCard q = new Flag();
        if (upgraded) q.upgrade();
        topDeck(q);
    }

    public void upp() {
        upgradeBlock(1);
        AbstractCard q = new Flag();
        q.upgrade();
        cardsToPreview = q;
        uDesc();
    }
}