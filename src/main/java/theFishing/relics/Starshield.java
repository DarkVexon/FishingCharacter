package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;
import theFishing.cards.StarOfTheShow;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.StarHelper.isStarCard;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.shuffleIn;

public class Starshield extends AbstractEasyRelic {
    public static final String ID = makeID("Starshield");

    public Starshield() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (isStarCard(targetCard)) {
            flash();
            atb(new GainBlockAction(AbstractDungeon.player, 1));
        }
    }
}
