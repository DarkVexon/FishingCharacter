package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theFishing.FishingMod.makeID;

public class QuietVigorPatch {
    @SpirePatch(
            clz = AbstractPower.class,
            method = "flash"
    )
    public static class QuietVigor {
        public static SpireReturn Prefix(AbstractPower __instance) {
            if (AbstractDungeon.player.hasPower(makeID("StaroftheShow")) && __instance.ID.equals(VigorPower.POWER_ID)) {
                __instance.flashWithoutSound();
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
