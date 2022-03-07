package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class SpoilerAlert extends AbstractFishingCard {
    public final static String ID = makeID("SpoilerAlert");
    // intellij stuff skill, self, common, , , , , , 1

    public SpoilerAlert() {
        super(ID, -1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            att(new ScryAction(effect + params[0]));
            return true;
        }, magicNumber));
        atb(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}