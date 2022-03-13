package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class WornCoat extends AbstractEasyRelic implements OnEnterDiscardRelic {
    public static final String ID = makeID("WornCoat");

    public WornCoat() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK, TheFishing.Enums.FISHING_COLOR);
    }

    public void onEnterDiscard(AbstractCard c) {
        if (c instanceof AbstractFishCard) {
            flash();
            atb(new GainBlockAction(AbstractDungeon.player, 4));
        }
    }
}
