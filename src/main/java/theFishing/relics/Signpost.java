package theFishing.relics;

import basemod.devcommands.relic.Relic;
import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.TheFishing;
import theFishing.cards.SignpostCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Signpost extends AbstractEasyRelic {
    public static final String ID = makeID("Signpost");

    public Signpost() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
        tips.clear();
        tips.add(new PowerTip(this.name, this.description));
        tips.add(new CardPowerTip(new SignpostCard()));
        initializeTips();
    }

    @Override
    public void atBattleStartPreDraw() {
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        makeInHand(new SignpostCard());
    }
}
