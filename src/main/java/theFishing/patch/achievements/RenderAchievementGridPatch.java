package theFishing.patch.achievements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import javassist.CtBehavior;

import java.util.Iterator;

@SpirePatch(clz = StatsScreen.class, method = "renderStatScreen")
public class RenderAchievementGridPatch {
    private static UIStrings uiStrings;

    static {
        // Load the UIStrings when the class is loaded
        uiStrings = CardCrawlGame.languagePack.getUIString("fishing:AchievementGrid");
    }
    @SpireInsertPatch(locator = Locator.class, localvars = {"renderY"})
    public static void Insert(StatsScreen __instance, SpriteBatch sb, @ByRef float[] renderY) {
        renderY[0] += 50.0F * Settings.scale;

        // Render the More Achievements section
        StatsScreen.renderHeader(sb, uiStrings.TEXT[0], 300.0F * Settings.scale, renderY[0]);
        StatsScreenPatch.getFishingAchievements().render(sb, renderY[0]);
        renderY[0] -= StatsScreenPatch.getFishingAchievements().calculateHeight();
        renderY[0] -= 100.0F * Settings.scale;
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(Iterator.class, "hasNext");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
