package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class HunkerDown extends AbstractFishingCard {
    public final static String ID = makeID("HunkerDown");
    // intellij stuff skill, self, common, , , , , , 

    public HunkerDown() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(p, p, 1, !upgraded));
        atb(new GainEnergyAction(1));
    }

    public void upp() {
        uDesc();
    }
}