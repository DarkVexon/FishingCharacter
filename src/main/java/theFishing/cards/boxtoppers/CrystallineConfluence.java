package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFishing.actions.EasyXCostAction;
import theFishing.cards.StarShard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class CrystallineConfluence extends AbstractBoxTopper {
    public final static String ID = makeID("CrystallineConfluence");
    // intellij stuff skill, self, , , , , , 

    public CrystallineConfluence() {
        super(ID, -1, CardType.SKILL, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 0;
        cardsToPreview = new StarShard();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                att(new MakeTempCardInDrawPileAction(new StarShard(), 1, true, true));
            }
            return true;
        }, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}
