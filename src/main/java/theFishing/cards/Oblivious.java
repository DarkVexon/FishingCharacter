package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class Oblivious extends AbstractFishingCard {
    public final static String ID = makeID("Oblivious");
    // intellij stuff skill, self, common, , , 6, 1, , 

    public Oblivious() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractCard q = AbstractFishCard.returnRandomFish();
        if (upgraded) q.upgrade();
        makeInHand(q);
    }

    public void upp() {
        upgradeBlock(1);
        uDesc();
    }
}