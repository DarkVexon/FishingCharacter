package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Signpost extends AbstractEasyRelic {
    public static final String ID = makeID("Signpost");

    public Signpost() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    public static final int BLOCK_GRANTED = 6;

    @Override
    public void atBattleStart() {
        grayscale = false;
    }

    @Override
    public void onVictory() {
        grayscale = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER && !grayscale) {
            flash();
            atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            atb(new GainBlockAction(AbstractDungeon.player, BLOCK_GRANTED));
            atb(new DrawCardAction(AbstractDungeon.player, 1));
            grayscale = true;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_GRANTED + DESCRIPTIONS[1];
    }
}
