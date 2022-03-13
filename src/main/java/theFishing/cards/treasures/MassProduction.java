package theFishing.cards.treasures;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class MassProduction extends AbstractTreasureCard {
    public final static String ID = makeID("MassProduction");
    // intellij stuff skill, self, , , , , 3, 1

    public MassProduction() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction("to duplicate for Mass Production.", (cards) -> {
            AbstractCard q = cards.get(0);
            q.updateCost(-999);
            att(new MakeTempCardInHandAction(q, magicNumber, true));
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}