package theFishing.patch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import theFishing.FishingMod;
import theFishing.util.Wiz;

public class ShowDelveTooltipPatch1 {

    public static boolean resetDraggingBackToTrue = false;
    public static boolean resetTargetingBackToTrue = false;

    @SpirePatch(
            clz = TipHelper.class,
            method = "render"
    )
    public static class UnsetDrag {
        public static void Prefix(SpriteBatch sb) {
            if (Wiz.isInCombat()) {
                if (AbstractDungeon.player.hoveredCard != null && AbstractDungeon.player.hoveredCard.hasTag(FishingMod.DELVES)) {
                    if (AbstractDungeon.player.isDraggingCard) {
                        AbstractDungeon.player.isDraggingCard = false;
                        resetDraggingBackToTrue = true;
                    } else if (AbstractDungeon.player.inSingleTargetMode) {
                        AbstractDungeon.player.inSingleTargetMode = false;
                        resetTargetingBackToTrue = true;
                    }
                }
            }
        }
    }
}
