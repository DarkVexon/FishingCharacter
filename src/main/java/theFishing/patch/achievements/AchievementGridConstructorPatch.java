package theFishing.patch.achievements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.stats.AchievementGrid;
import theFishing.FishingMod;
import theFishing.achievements.FishingAchievementItem;

@SpirePatch(clz = AchievementGrid.class, method = "<ctor>")
public class AchievementGridConstructorPatch {
    public AchievementGridConstructorPatch() {}

    @SpirePostfixPatch
    public static void Postfix(AchievementGrid instance) {
        FishingAchievementItem.atlas = new TextureAtlas(Gdx.files.internal("fishingResources/images/achievements/AdventurerAchievements.atlas"));
        loadAchievement(instance, "STARLIGHT", false);
        loadAchievement(instance, "OLD_TIMES", false);
        loadAchievement(instance, "ETERNITY", false);
        loadAchievement(instance, "DELVE_GRADUATE", false);
        loadAchievement(instance, "ADVENTURER_MASTERY", false);
    }

    private static void loadAchievement(AchievementGrid instance, String id, boolean isHidden) {
        String fullId = FishingMod.makeID(id);
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(fullId);
        String name = uiStrings.TEXT[0];
        String description = uiStrings.TEXT[1];

        TextureAtlas.AtlasRegion AchievementImageUnlocked = FishingAchievementItem.atlas.findRegion("unlocked/" + id);
        TextureAtlas.AtlasRegion AchievementImageLocked = FishingAchievementItem.atlas.findRegion("locked/" + id);

        instance.items.add(new FishingAchievementItem(name, description, fullId, isHidden, AchievementImageUnlocked, AchievementImageLocked));
    }
}