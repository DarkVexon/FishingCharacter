package theFishing.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class AddCopyNextTurnPower extends AbstractAdventurerPower implements NonStackablePower {
    public static String ID = makeID(AddCopyNextTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    private AbstractCard tar;

    public AddCopyNextTurnPower(AbstractCard tar) {
        super(ID, powerStrings.NAME, PowerType.BUFF, true, AbstractDungeon.player, -1);
        canGoNegative = false;
        this.tar = tar;
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        makeInHand(tar.makeStatEquivalentCopy());
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        if (tar != null) description = powerStrings.DESCRIPTIONS[0] + FontHelper.colorString(tar.name, "y") + powerStrings.DESCRIPTIONS[1];
    }
}
