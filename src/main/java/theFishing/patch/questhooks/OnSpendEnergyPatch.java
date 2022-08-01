package theFishing.patch.questhooks;

import basemod.devcommands.energy.Energy;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.EnergyManager;
import theFishing.quest.QuestHelper;

public class OnSpendEnergyPatch {
    @SpirePatch(
            clz = EnergyManager.class,
            method = "use"
    )
    public static class QuestDetectEnergySpentPatch {
        public static void Postfix(EnergyManager __instance, int e) {
            QuestHelper.onSpendEnergy(e);
        }
    }
}
