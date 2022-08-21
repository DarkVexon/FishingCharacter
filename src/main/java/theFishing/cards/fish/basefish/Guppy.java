package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;

public class Guppy extends AbstractFishCard {
    public final static String ID = makeID("Guppy");
    // intellij stuff skill, self, special, , , 3, 2, , 

    public Guppy() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);
        baseBlock = 3;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
}