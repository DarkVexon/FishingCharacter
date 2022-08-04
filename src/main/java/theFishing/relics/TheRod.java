package theFishing.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;

public class TheRod extends AbstractEasyRelic {
    public static final String ID = makeID("TheRod");

    public TheRod() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStart() {
        counter = 3;
        grayscale = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (!grayscale && !drawnCard.upgraded && FoilPatches.isFoil(drawnCard)) {
            counter -= 1;
            drawnCard.upgrade();
            if (counter == 0) {
                grayscale = true;
                counter = -1;
            }
        }
    }

    @Override
    public void onUnequip() {
        if (AbstractDungeon.player instanceof TheFishing) {
            ((TheFishing) AbstractDungeon.player).onLoseStartingRod();
        }
    }
}
