package theFishing.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Offering;
import theFishing.FishingMod;
import theFishing.cards.StarOfTheShow;

import java.util.ArrayList;
import java.util.Arrays;

public class StarHelper {

    private static String[] basegameStarCards = {
        Offering.ID
    };

    public static boolean isStarCard(AbstractCard c) {
        return getStarNumber(c) > 0;
    }

    public static int getStarNumber(AbstractCard c) {
        return c.hasTag(FishingMod.STAR_IN_ART) || Arrays.stream(basegameStarCards).anyMatch(s -> s.equals(c.cardID)) ? 1 : 0;
    }

}
