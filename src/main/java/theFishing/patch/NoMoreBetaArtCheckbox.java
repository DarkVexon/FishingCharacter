package theFishing.patch;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import theFishing.cards.AbstractFishingCard;

@SpirePatch(clz = SingleCardViewPopup.class, method = "canToggleBetaArt")
public class NoMoreBetaArtCheckbox {
    public static SpireReturn<Boolean> Prefix(SingleCardViewPopup __instance) {
        AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
        if (card instanceof AbstractFishingCard) {
            return SpireReturn.Return(false);
        }
        return SpireReturn.Continue();
    }
}
