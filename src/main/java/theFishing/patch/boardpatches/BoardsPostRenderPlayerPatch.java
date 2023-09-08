package theFishing.patch.boardpatches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import theFishing.FishingMod;
import theFishing.util.Wiz;

@SpirePatch(clz = AbstractPlayer.class, method = "render")
public class BoardsPostRenderPlayerPatch {
    public static void Postfix(AbstractPlayer obj, SpriteBatch sb) {
        if (Wiz.isInCombat()) {
            if (FishingMod.activeBoard != null && FishingMod.activeBoard.shouldBeActive()) {
                FishingMod.activeBoard.postPlayerRender(sb);
            }
        }
    }
}