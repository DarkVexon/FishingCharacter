package theFishing.patch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;

public class ShowDelveTooltipPatch2 {

    @SpirePatch(
            clz = TipHelper.class,
            method = "render"
    )
    public static class ResetDrag {
        public static void Postfix(SpriteBatch sb) {
            if (ShowDelveTooltipPatch1.resetDraggingBackToTrue) {
                AbstractDungeon.player.isDraggingCard = true;
            }
            if (ShowDelveTooltipPatch1.resetTargetingBackToTrue) {
                AbstractDungeon.player.inSingleTargetMode = true;
            }
            ShowDelveTooltipPatch1.resetDraggingBackToTrue = false;
            ShowDelveTooltipPatch1.resetTargetingBackToTrue = false;
        }
    }
}
