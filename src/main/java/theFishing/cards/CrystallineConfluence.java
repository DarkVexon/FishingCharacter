package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class CrystallineConfluence extends AbstractFishingCard {
    public final static String ID = makeID("CrystallineConfluence");
    // intellij stuff skill, self, uncommon, , , , , 0, 1

    public CrystallineConfluence() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
        AbstractCard q = new StarShard();
        makeFoil(q);
        cardsToPreview = q;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            AbstractCard q = new StarShard();
            makeFoil(q);
            att(new MakeTempCardInDrawPileAction(q, effect + params[0], true, false));
            return true;
        }, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}