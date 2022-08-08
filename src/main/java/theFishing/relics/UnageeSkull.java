package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import theFishing.TheFishing;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class UnageeSkull extends AbstractEasyRelic {
    public static final String ID = makeID("UnageeSkull");

    public UnageeSkull() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStart() {
        flash();
        atb(new MakeTempCardInDiscardAndDeckAction(AbstractFishCard.returnRandomFish()));
    }
}
