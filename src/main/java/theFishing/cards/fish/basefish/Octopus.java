package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Octopus extends AbstractFishCard {
    public final static String ID = makeID("Octopus");
    // intellij stuff skill, none, special, , , , , 2, 1

    public Octopus() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
    }
}