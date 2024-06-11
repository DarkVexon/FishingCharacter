package theFishing.patch.achievements;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;

@SpirePatch2(
        clz = StatsScreen.class,
        method = "renderStatScreen"
)
public class StatScreenOffsetPatch {
    public static Integer NEW_ACHIEVEMENTS = 5;

    public StatScreenOffsetPatch() {
    }

    @SpireInsertPatch(
            rloc = 8,
            localvars = {"renderY"}
    )
    public static void Insert(StatsScreen __instance, @ByRef float[] renderY) {
        renderY[0] -= (float)(36 * NEW_ACHIEVEMENTS) * Settings.scale;
    }
}