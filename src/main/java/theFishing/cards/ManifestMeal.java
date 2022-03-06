package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EasyXCostAction;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ManifestMeal extends AbstractFishingCard {
    public final static String ID = makeID("ManifestMeal");
    // intellij stuff skill, self, uncommon, , , , , 0, 1

    public ManifestMeal() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            Food q = new Food();
            q.setX(effect + params[0]);
            shuffleIn(q);
            return true;
        }, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}