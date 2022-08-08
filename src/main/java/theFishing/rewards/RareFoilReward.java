package theFishing.rewards;

import basemod.abstracts.CustomReward;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.patch.RewardItemTypeEnumPatch;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.patch.foil.FoilPatches.makeFoil;

public class RareFoilReward extends CustomReward {
    public static final String ID = makeID("RareFoilReward");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString("bronze:SpecificCardReward").TEXT;

    public RareFoilReward() {
        super(TexLoader.getTexture(makeImagePath("rewards/RareFoilReward.png")), "Choose a Rare Foil card.", RewardItemTypeEnumPatch.RAREFOIL);
        cards.clear();
        for (int i = 0; i < 3; i++) {
            AbstractCard q = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE).makeCopy();
            cards.add(q);
        }

        boolean containsDupe = true;
        while (containsDupe) {
            containsDupe = false;
        }
    }

    @Override
    public boolean claimReward() {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, "Choose a card to obtain.");
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        return false;
    }
}