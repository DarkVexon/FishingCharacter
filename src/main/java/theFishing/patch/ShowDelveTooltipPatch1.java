package theFishing.patch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import theFishing.FishingMod;
import theFishing.cards.StartOfTurnInExhaustCard;
import theFishing.util.Wiz;

public class ShowDelveTooltipPatch1 {

    public static boolean resetBackToTrue = false;

    @SpirePatch(
            clz = TipHelper.class,
            method = "render"
    )
    public static class UnsetDrag {
        public static void Prefix(SpriteBatch sb) {
            if (Wiz.isInCombat() && AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.hoveredCard.hasTag(FishingMod.DELVES)) {
                AbstractDungeon.player.isDraggingCard = false;
                resetBackToTrue = true;
            }
        }
    }
}
