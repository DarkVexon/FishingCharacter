package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSleepEffect;

public class OnRestPatch {
    @SpirePatch(
            clz = CampfireSleepEffect.class,
            method = "playSleepJingle"
    )
    public static class triggerRestCards {
        public static void Postfix(CampfireSleepEffect __instance) {
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (c instanceof OnRestCard) {
                    ((OnRestCard) c).onRest();
                }
            }
        }
    }
}
