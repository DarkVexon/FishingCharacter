package theFishing.util;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;

import static com.megacrit.cardcrawl.unlock.UnlockTracker.achievementPref;

public class FishingAchievementUnlocker {
    public static void unlockAchievement(String key) {
        if (!Settings.isShowBuild && Settings.isStandardRun()) {
            if (!achievementPref.getBoolean(key, false)) {
                achievementPref.putBoolean(key, true);
            }

            achievementPref.flush();
        }
    }
}
