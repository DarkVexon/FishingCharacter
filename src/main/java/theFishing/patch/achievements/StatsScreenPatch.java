package theFishing.patch.achievements;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import theFishing.achievements.FishingAchievementGrid;

@SpirePatch(clz = StatsScreen.class, method = SpirePatch.CLASS)
public class StatsScreenPatch {
    private static FishingAchievementGrid fishingAchievements;

    public static FishingAchievementGrid getFishingAchievements() {
        if (fishingAchievements == null) {
            fishingAchievements = new FishingAchievementGrid();
        }
        return fishingAchievements;
    }
}