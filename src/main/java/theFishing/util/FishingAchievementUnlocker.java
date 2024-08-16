package theFishing.util;

import com.megacrit.cardcrawl.core.Settings;
import theFishing.FishingMod;

import static com.megacrit.cardcrawl.unlock.UnlockTracker.achievementPref;

public class FishingAchievementUnlocker {
    public static void unlockAchievement(String key) {
        String fullKey = FishingMod.makeID(key);
        if (!Settings.isShowBuild && Settings.isStandardRun()) {
            if (!achievementPref.getBoolean(fullKey, false)) {
                achievementPref.putBoolean(fullKey, true);
            }

            achievementPref.flush();
        }
    }
}