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

    private static final int CARDS_UPGRADED = 3;

    @Override
    public void atBattleStart() {
        counter = CARDS_UPGRADED;
        grayscale = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (!grayscale && !drawnCard.upgraded && FoilPatches.isFoil(drawnCard)) {
            counter -= 1;
            drawnCard.upgrade();
            drawnCard.superFlash();
            drawnCard.applyPowers();
            if (counter == 0) {
                grayscale = true;
                counter = -1;
            }
        }
    }

    @Override
    public void onVictory() {
        grayscale = false;
        counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CARDS_UPGRADED + DESCRIPTIONS[1];
    }
}
