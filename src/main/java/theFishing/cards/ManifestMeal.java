package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ManifestMeal extends AbstractFishingCard {
    public final static String ID = makeID("ManifestMeal");
    // intellij stuff skill, self, uncommon, , , , , 0, 1

    public ManifestMeal() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Food();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            Food q = new Food();
            q.setX(effect + params[0]);
            att(new MakeTempCardInDrawPileAction(q, 1, false, true));
            return true;
        }, magicNumber));
    }


    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}