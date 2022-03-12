//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package theFishing.patch;

import com.evacipated.cardcrawl.mod.stslib.RelicTools;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.relics.DeadBranch;
import com.megacrit.cardcrawl.relics.StrangeSpoon;
import theFishing.TheFishing;

import java.util.Arrays;
import java.util.List;

public class BanRelicsPatch {

    public static List<String> ourList = Arrays.asList(DeadBranch.ID, ChemicalX.ID, StrangeSpoon.ID);

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "returnEndRandomRelicKey"
    )
    public static class EndRandomRelic {
        private static int depth = 0;

        public static String Postfix(String __result, RelicTier tier) {
            if (depth == 0 && ourList.contains(__result) && AbstractDungeon.player instanceof TheFishing) {
                RelicTools.returnRelicToPool(tier, __result);
                ++depth;
                __result = AbstractDungeon.returnEndRandomRelicKey(tier);
                --depth;
            }

            return __result;
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "returnRandomRelicKey"
    )
    public static class RandomRelic {
        private static int depth = 0;

        public static String Postfix(String __result, RelicTier tier) {
            if (depth == 0 && ourList.contains(__result) && AbstractDungeon.player instanceof TheFishing) {
                RelicTools.returnRelicToPool(tier, __result);
                ++depth;
                __result = AbstractDungeon.returnRandomRelicKey(tier);
                --depth;
            }
            return __result;
        }
    }
}