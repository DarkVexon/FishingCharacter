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
            if (ShowDelveTooltipPatch1.resetBackToTrue) {
                AbstractDungeon.player.isDraggingCard = true;
            }
            ShowDelveTooltipPatch1.resetBackToTrue = false;
        }
    }
}
