package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import theFishing.FishingMod;
import theFishing.quest.QuestHelper;

public class OnDrawCard {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "draw",
            paramtypez = {int.class}
    )
    public static class OnDrawCardPatch {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"c"}
        )
        public static void onDraw(AbstractPlayer __instance, AbstractCard c) {
            if (PreDrawPatch.DRAWN_DURING_TURN) {
                FishingMod.voyagedCards.add(c);
            }
            QuestHelper.quests.stream().forEach(q -> q.onDrawCard());
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "powers");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}