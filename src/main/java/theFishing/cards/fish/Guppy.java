package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Guppy extends AbstractFishCard {
    public final static String ID = makeID("Guppy");
    // intellij stuff skill, self, special, , , 3, 2, , 

    public Guppy() {
        super(ID, 0, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF, AbstractCard.CardColor.COLORLESS);
        baseBlock = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeBlock(2);
    }
}