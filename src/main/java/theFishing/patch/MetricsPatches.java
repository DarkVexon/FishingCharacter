package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.Metrics;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theFishing.TheFishing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MetricsPatches {

    private static final Logger logger = LogManager.getLogger(MetricsPatches.class);
    private static final String url = "https://script.google.com/macros/s/AKfycbxyJtdr_l29Ea9LCOMld0p9eNL0ylXr9C8qUMmu7mIRJsBpERNkkwZRc6bRnMl4X_c1/exec";

    private static boolean shouldSendInfo() {
        return AbstractDungeon.player.chosenClass == TheFishing.Enums.THE_FISHING;
    }

    @SpirePatch(clz = GameOverScreen.class, method = "shouldUploadMetricData")
    public static class ShouldUploadMetricData {

        public static boolean Postfix(boolean returnValue) {
            if (shouldSendInfo()) {
                returnValue = Settings.UPLOAD_DATA;
            }
            return returnValue;
        }

    }

    @SpirePatch(clz = Metrics.class, method = "run")
    public static class RunPatch {
        public static void Postfix(Metrics metrics) {
            if (metrics.type == Metrics.MetricRequestType.UPLOAD_METRICS && shouldSendInfo()) {
                try {
                    Method m = Metrics.class.getDeclaredMethod("sendPost", String.class, String.class);
                    m.setAccessible(true);
                    m.invoke(metrics, url, null);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    logger.error("Exception while sending metrics", e);
                }
            }
        }

    }
}