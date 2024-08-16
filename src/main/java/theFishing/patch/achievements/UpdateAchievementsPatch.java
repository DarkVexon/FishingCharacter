package theFishing.patch.achievements;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import theFishing.FishingMod;

@SpirePatch(clz = StatsScreen.class, method = "update")
public class UpdateAchievementsPatch {
    public static void Postfix(StatsScreen __instance) {
        FishingMod.fishingAchievementGrid.update();
    }
}