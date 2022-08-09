package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
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
        atb(new MakeTempCardInDrawPileAction(AbstractFishCard.returnRandomFish(), 1, true, true));
        atb(new MakeTempCardInDiscardAction(AbstractFishCard.returnRandomFish(), 1));
    }
}
