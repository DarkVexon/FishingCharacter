package theFishing.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.TheFishing;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;

public class TheRod extends AbstractAdventurerRelic {
    public static final String ID = makeID("TheRod");

    public TheRod() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStart() {
        grayscale = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (!grayscale && FoilPatches.isFoil(drawnCard) && drawnCard.costForTurn != 0 && !drawnCard.freeToPlayOnce) {
            flash();
            drawnCard.updateCost(-99);
            drawnCard.superFlash();
            grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
