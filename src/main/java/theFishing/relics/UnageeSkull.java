package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;

public class UnageeSkull extends AbstractAdventurerRelic {
    public static final String ID = makeID(UnageeSkull.class.getSimpleName());

    public UnageeSkull() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(AbstractFishCard.returnRandomFish(), 1, false));
    }
}
