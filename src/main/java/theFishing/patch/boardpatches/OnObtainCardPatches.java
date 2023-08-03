package theFishing.patch.boardpatches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theFishing.FishingMod;
import theFishing.TheFishing;

public class OnObtainCardPatches {
    @SpirePatch(clz = ShowCardAndObtainEffect.class, method = "update")
    public static class OnPickupCardDoStuffPatch {
        public static void Postfix(ShowCardAndObtainEffect __instance) {
            AbstractCard q = (AbstractCard) ReflectionHacks.getPrivate(__instance, ShowCardAndObtainEffect.class, "card");
            if (__instance.isDone && AbstractDungeon.player.chosenClass.equals(TheFishing.Enums.THE_FISHING)) {
                FishingMod.activeBoard.onObtainCard(q);
            }
        }
    }

    @SpirePatch(clz = FastCardObtainEffect.class, method = "update")
    public static class OnPickupCardDoStuffPatch2 {
        public static void Postfix(FastCardObtainEffect __instance) {
            AbstractCard q = (AbstractCard) ReflectionHacks.getPrivate(__instance, FastCardObtainEffect.class, "card");
            if (__instance.isDone && AbstractDungeon.player.chosenClass.equals(TheFishing.Enums.THE_FISHING)) {
                FishingMod.activeBoard.onObtainCard(q);
            }
        }
    }
}
