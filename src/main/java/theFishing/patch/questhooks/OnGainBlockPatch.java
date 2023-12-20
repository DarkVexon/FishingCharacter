package theFishing.patch.questhooks;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import javassist.CtBehavior;
import theFishing.quest.QuestHelper;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "addBlock"
)
public class OnGainBlockPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void triggerOnDeath(AbstractCreature __instance, int blockAmount) {
        QuestHelper.onGainBlock(blockAmount);
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCreature.class, "powers");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}