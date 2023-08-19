package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CtBehavior;
import theFishing.powers.OnGainEnergyPower;
import theFishing.powers.OnRefreshHandPower;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "addEnergy"
)
public class OnGainEnergyPowerPatch {
    public static boolean shouldTrigger = true;

    public static void Postfix(int e) {
        if (shouldTrigger) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnGainEnergyPower) {
                    ((OnGainEnergyPower) p).onGainEnergy(e);
                }
            }
        }
    }
}