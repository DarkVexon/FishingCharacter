package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theFishing.relics.OnEnterDiscardRelic;

@SpirePatch(
        clz = CardGroup.class,
        method = "moveToDiscardPile"
)
public class OnEnterDiscardHook {
    public static void Prefix(CardGroup __instance, AbstractCard c) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof OnEnterDiscardRelic) {
                ((OnEnterDiscardRelic) r).onEnterDiscard(c);
            }
        }
    }
}
