package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Rainbowscales extends AbstractFishCard {
    public final static String ID = makeID("Rainbowscales");
    // intellij stuff skill, self, , , , , 2, 1

    public Rainbowscales() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard q = Wiz.returnTrulyRandomPrediCardInCombat(c -> !c.hasTag(CardTags.HEALING) && c.type != CardType.STATUS && c.type != CardType.CURSE && c.color != CardColor.CURSE, true);
            q.setCostForTurn(0);
            makeInHand(q);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}