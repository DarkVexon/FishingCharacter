package theFishing.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class TheBackpack extends AbstractFishingCard {
    public final static String ID = makeID("TheBackpack");
    // intellij stuff skill, self, rare, , , , , , 

    public TheBackpack() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DiscardAction(p, p, BaseMod.MAX_HAND_SIZE, true));
        atb(new BetterDiscardPileToHandAction(1));
    }

    public void upp() {
        upgradeBlock(3);
    }
}