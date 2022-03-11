package theFishing.relics;

import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theFishing.TheFishing;
import theFishing.cards.SignpostCard;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class Fishbowl extends AbstractEasyRelic {
    public static final String ID = makeID("Fishbowl");

    public Fishbowl() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(AbstractFishCard.returnRandomFish(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(AbstractFishCard.returnRandomFish(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
    }
}
