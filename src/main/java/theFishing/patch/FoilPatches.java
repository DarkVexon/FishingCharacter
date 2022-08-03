package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class FoilPatches {
    public static boolean isFoil(AbstractCard card) {
        return FoilField.isFoil.get(card);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class FoilField {
        public static SpireField<Boolean> isFoil = new SpireField<>(() -> false);
    }
}
