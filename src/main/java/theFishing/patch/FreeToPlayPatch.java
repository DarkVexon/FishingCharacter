package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.powers.NextSkillFreePower;
import theFishing.util.Wiz;

public class FreeToPlayPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "freeToPlay"
    )
    public static class FreeToPlaySkills {
        public static boolean Postfix(boolean __result, AbstractCard __instance) {
            if (Wiz.isInCombat() && AbstractDungeon.player.hasPower(NextSkillFreePower.ID) && __instance.type.equals(AbstractCard.CardType.SKILL)) {
                return true;
            }
            return __result;
        }
    }
}
