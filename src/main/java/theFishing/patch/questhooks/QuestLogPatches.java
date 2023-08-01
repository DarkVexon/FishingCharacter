package theFishing.patch.questhooks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import theFishing.FishingMod;
import theFishing.TheFishing;
import theFishing.quest.QuestHelper;
import theFishing.util.Wiz;

public class QuestLogPatches {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "preBattlePrep"
    )
    public static class PreBattlePrep {
        public static void Prefix(AbstractPlayer __instance) {
            QuestHelper.reset();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "render"
    )
    public static class ShowQuests {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractDungeon __instance, SpriteBatch sb) {
            if (AbstractDungeon.rs == AbstractDungeon.RenderScene.NORMAL) {
                if (Wiz.isInCombat()) {
                    if (!QuestHelper.quests.isEmpty()) {
                        QuestHelper.render(sb);
                    }
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getCurrRoom");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}