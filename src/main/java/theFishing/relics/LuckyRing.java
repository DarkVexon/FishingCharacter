package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class LuckyRing extends AbstractAdventurerRelic {
    public static final String ID = makeID("LuckyRing");

    public LuckyRing() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStart() {
        counter = 0;
    }

    @Override
    public void onVictory() {
        counter = -1;
        grayscale = false;
    }


    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }

        if (this.counter == 2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(1));
            addToBot(new GainEnergyAction(2));
            this.counter = -1;
            this.grayscale = true;
        }

    }
}
