package theFishing.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.cards.Flight;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.topDeck;

public class FirstClassPower extends AbstractAdventurerPower {
    public static String ID = makeID(FirstClassPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public FirstClassPower() {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, 1);
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            AbstractCard q = new Flight();
            makeFoil(q);
            this.addToBot(new MakeTempCardInHandAction(q, this.amount, false));
        }
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + (amount == 1 ? powerStrings.DESCRIPTIONS[1] : powerStrings.DESCRIPTIONS[2]);
    }
}
