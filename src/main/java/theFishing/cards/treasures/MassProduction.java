package theFishing.cards.treasures;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class MassProduction extends AbstractTreasureCard {
    public final static String ID = makeID("MassProduction");
    // intellij stuff skill, self, , , , , 3, 1

    public MassProduction() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsAction(AbstractDungeon.player.masterDeck.group, "to duplicate for Mass Production.", (cards) -> {
            AbstractCard q = cards.get(0).makeCopy();
            q.updateCost(-999);
            att(new MakeTempCardInHandAction(q, magicNumber, true));
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}