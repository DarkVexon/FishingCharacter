package theFishing.patch.achievements;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;

@SpirePatch2(
        clz = StatsScreen.class,
        method = "calculateScrollBounds"
)
public class StatScreenScrollbarPatch {
    @SpirePostfixPatch
    public static void Postfix(StatsScreen __instance) {
        float currentUpperBound = (Float) ReflectionHacks.getPrivate(__instance, StatsScreen.class, "scrollUpperBound");
        float fishingAchievementsHeight = StatsScreenPatch.getFishingAchievements().calculateHeight();
        ReflectionHacks.setPrivate(__instance, StatsScreen.class, "scrollUpperBound", currentUpperBound + (fishingAchievementsHeight + 50F));
    }
}