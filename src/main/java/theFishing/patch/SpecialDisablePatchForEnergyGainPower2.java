package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.core.EnergyManager;

@SpirePatch2(clz = EnergyManager.class, method = "recharge")
public class SpecialDisablePatchForEnergyGainPower2 {
    public static void Postfix() {
        OnGainEnergyPowerPatch.shouldTrigger = true;
    }
}