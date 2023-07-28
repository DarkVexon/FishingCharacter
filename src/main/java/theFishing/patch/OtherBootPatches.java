package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.SharpHidePower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theFishing.relics.OtherBoot;

public class OtherBootPatches {
    @SpirePatch(
            clz = ThornsPower.class,
            method = "onAttacked"
    )
    public static class BootNoThorns {
        public static SpireReturn<Integer> Prefix(ThornsPower __instance, DamageInfo info, int damageAmount) {
            if (__instance.owner != AbstractDungeon.player && AbstractDungeon.player.hasRelic(OtherBoot.ID)) {
                AbstractDungeon.player.getRelic(OtherBoot.ID).flash();
                return SpireReturn.Return(damageAmount);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = SharpHidePower.class,
            method = "onUseCard"
    )
    public static class BootNoSharpHide {
        public static SpireReturn Prefix(SharpHidePower __instance, AbstractCard c, UseCardAction action) {
            if (__instance.owner != AbstractDungeon.player && AbstractDungeon.player.hasRelic(OtherBoot.ID) && c.type == AbstractCard.CardType.ATTACK) {
                AbstractDungeon.player.getRelic(OtherBoot.ID).flash();
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
