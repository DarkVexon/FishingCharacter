package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class SignpostCard extends AbstractFishingCard {
    public final static String ID = makeID("SignpostCard");
    // intellij stuff skill, self, special, , , , , 1, 1

    public SignpostCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
        selfRetain = true;
        exhaust = true;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(" to duplicate.", (cards) -> {
            AbstractCard q = cards.get(0).makeStatEquivalentCopy();
            att(new MakeTempCardInDrawPileAction(q, 1, true, true));
        }));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}