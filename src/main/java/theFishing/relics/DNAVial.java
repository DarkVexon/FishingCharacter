package theFishing.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.TheFishing;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;

public class DNAVial extends AbstractEasyRelic {
    public static final String ID = makeID("DNAVial");

    public DNAVial() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheFishing.Enums.FISHING_COLOR);
        counter = -1;
    }

    @Override
    public void atBattleStart() {
        counter = 2;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard instanceof AbstractFishCard && counter > 0) {
            flash();
            counter--;
            AbstractCard q = targetCard.makeCopy();
            q.upgrade();
            shuffleIn(q);
            if (counter == 0) {
                grayscale = true;
            }
        }
    }

    @Override
    public void onVictory() {
        counter = -1;
        grayscale = false;
    }
}
