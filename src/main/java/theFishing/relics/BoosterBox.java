package theFishing.relics;

import basemod.abstracts.CustomSavable;
import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theFishing.TheFishing;
import theFishing.cards.boxtoppers.*;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.Wiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;

public class BoosterBox extends AbstractEasyRelic implements CustomSavable<String> {
    public static final String ID = makeID("BoosterBox");

    private AbstractCard boosterBoxBonus;
    private static ArrayList<String> possibleBoosters;

    public BoosterBox() {
        super(ID, RelicTier.SHOP, LandingSound.SOLID, TheFishing.Enums.FISHING_COLOR);

        if (CardCrawlGame.isInARun()) {
            if (possibleBoosters == null) {
                possibleBoosters = new ArrayList<>();
                possibleBoosters.add(HelloThere.ID);
                possibleBoosters.add(Hindsight.ID);
                possibleBoosters.add(ScalingIsWin.ID);
                possibleBoosters.add(TheKeyReturns.ID);
                possibleBoosters.add(ZaHando.ID);
                possibleBoosters.add(BeastSlayer.ID);
                possibleBoosters.add(CrystallineConfluence.ID);
            }

            boosterBoxBonus = CardLibrary.getCard(Wiz.getRandomItem(possibleBoosters, AbstractDungeon.cardRng)).makeCopy();
            makeFoil(boosterBoxBonus);

            description = getUpdatedDescription();

            tips.clear();
            tips.add(new PowerTip(name, description));
            tips.add(new CardPowerTip(boosterBoxBonus.makeStatEquivalentCopy()));
            initializeTips();
        }
    }

    public void onEquip() {
        ArrayList<AbstractCard> upgradableCards = new ArrayList();

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade() && !FoilPatches.isFoil(c)) {
                upgradableCards.add(c);
            }
        }

        if (upgradableCards.size() < 2) {
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (c.canUpgrade() && FoilPatches.isFoil(c)) {
                    upgradableCards.add(c);
                }
            }
        }

        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        if (!upgradableCards.isEmpty()) {
            if (upgradableCards.size() == 1) {
                upgradableCards.get(0).upgrade();
                makeFoil(upgradableCards.get(0));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy()));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            } else {
                upgradableCards.get(0).upgrade();
                makeFoil(upgradableCards.get(0));
                upgradableCards.get(1).upgrade();
                makeFoil(upgradableCards.get(1));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F - 20.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(upgradableCards.get(1).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F + 20.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            }
        }

        AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(boosterBoxBonus.makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
    }

    @Override
    public String getUpdatedDescription() {
        if (boosterBoxBonus != null) {
            return DESCRIPTIONS[1] + FontHelper.colorString(boosterBoxBonus.name, "y") + LocalizedStrings.PERIOD;
        }
        return DESCRIPTIONS[0];
    }

    @Override
    public String onSave() {
        return boosterBoxBonus.cardID;
    }

    @Override
    public void onLoad(String s) {
        boosterBoxBonus = CardLibrary.getCard(s).makeCopy();
        makeFoil(boosterBoxBonus);

        description = getUpdatedDescription();

        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new CardPowerTip(boosterBoxBonus.makeStatEquivalentCopy()));
        initializeTips();
    }
}
