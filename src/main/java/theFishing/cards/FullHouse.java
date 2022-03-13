package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class FullHouse extends AbstractFishingCard {
    public final static String ID = makeID("FullHouse");
    // intellij stuff power, self, rare, , , , , , 

    public FullHouse() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsAction(p.drawPile.group, "Choose a card to duplicate.", (cards) -> {
            addToTop(new MakeTempCardInDrawPileAction(cards.get(0).makeStatEquivalentCopy(), magicNumber, true, true));
        }));
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}