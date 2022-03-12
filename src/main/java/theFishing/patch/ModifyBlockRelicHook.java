package theFishing.patch;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theFishing.relics.ModifyBlockRelic;
import theFishing.relics.OnEnterDiscardRelic;

@SpirePatch(
        clz = AbstractCard.class,
        method = "applyPowersToBlock"
)
public class ModifyBlockRelicHook {
    public static void Postfix(AbstractCard __instance) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof ModifyBlockRelic) {
                __instance.block = MathUtils.floor(((ModifyBlockRelic) r).modifyBlock(__instance.block, __instance));
            }
        }
    }
}
