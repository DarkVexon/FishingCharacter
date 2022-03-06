package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EasyXCostAction;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class BagOfDefends extends AbstractFishingCard {
    public final static String ID = makeID("BagOfDefends");
    // intellij stuff skill, self, common, , , 5, 3, , 

    public BagOfDefends() {
        super(ID, -1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                att(new GainBlockAction(p, block));
            }
            return true;
        }));
    }

    public void upp() {
        upgradeBlock(3);
        uDesc();
    }
}