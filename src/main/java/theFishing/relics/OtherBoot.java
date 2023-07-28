package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.TheFishing;
import theFishing.cards.fish.Boot;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class OtherBoot extends AbstractAdventurerRelic {
    public static final String ID = makeID(OtherBoot.class.getSimpleName());

    public OtherBoot() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.cardID.equals(Boot.ID)) {
            flash();
            atb(new GainBlockAction(AbstractDungeon.player, 6));
        }
    }
}
