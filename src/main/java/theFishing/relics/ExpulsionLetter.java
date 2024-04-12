package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class ExpulsionLetter extends AbstractAdventurerRelic {
    public static final String ID = makeID(ExpulsionLetter.class.getSimpleName());

    public ExpulsionLetter() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atTurnStartPostDraw() {
        flash();
        AbstractCard result = null;
        int x = AbstractDungeon.cardRandomRng.random(0, 2);
        switch (x) {
            case 0:
                result = new Miracle();
                break;
            case 1:
                result = new Smite();
                break;
            case 2:
                result = new Safety();
                break;
        }
        makeFoil(result);
        makeInHand(result);
    }
}
