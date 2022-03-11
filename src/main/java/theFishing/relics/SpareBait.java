package theFishing.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.TheFishing;
import theFishing.cards.fish.Piranha;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class SpareBait extends AbstractEasyRelic {
    public static final String ID = makeID("SpareBait");

    public SpareBait() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.hand.isEmpty()) {
            flash();
            applyToSelf(new StrengthPower(AbstractDungeon.player, 1));
        }
    }
}
