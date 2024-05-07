package theFishing.patch.achievements;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.screens.stats.AchievementItem;
import theFishing.achievements.FishingAchievementItem;

@SpirePatch(
        clz = AchievementItem.class,
        method = "reloadImg"
)
public class FishingAchievementReloadImgPatch {
    public FishingAchievementReloadImgPatch() {
    }

    @SpirePostfixPatch
    public static void Postfix(AchievementItem __instance) {
        if (__instance instanceof FishingAchievementItem) {
            ((FishingAchievementItem)__instance).currentImg = FishingAchievementItem.atlas.findRegion(((FishingAchievementItem)__instance).currentImg.name);
        }

    }
}
