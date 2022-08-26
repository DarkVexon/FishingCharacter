package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFishing.actions.EasyXCostAction;
import theFishing.cards.StarShard;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class CrystallineConfluence extends AbstractBoxTopper {
    public final static String ID = makeID("CrystallineConfluence");
    // intellij stuff skill, self, , , , , , 

    public CrystallineConfluence() {
        super(ID, -1, CardType.SKILL, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 0;
        AbstractCard q = new StarShard();
        makeFoil(q);
        cardsToPreview = q;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        atb(new EasyXCostAction(this, (effect, params) -> {
            AbstractCard q = new StarShard();
            makeFoil(q);
            for (int i = 0; i < effect + params[0]; i++) {
                att(new MakeTempCardInDrawPileAction(q, 1, true, true));
            }
            return true;
        }, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}
