package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.shop.StoreRelic;
import theFishing.cards.OnBuyRelicCard;

public class MerchantsStrikePatch {
    @SpirePatch(
            clz = StoreRelic.class,
            method = "purchaseRelic"
    )
    public static class OnBuyRelicDoaAThing {
        public static void Prefix(StoreRelic __instance) {
            if (AbstractDungeon.player.gold >= __instance.price) {
                AbstractDungeon.player.masterDeck.group.stream().forEach(q -> {
                    if (q instanceof OnBuyRelicCard) {
                        ((OnBuyRelicCard) q).onBuyRelic(__instance.relic, __instance.price);
                    }
                });
            }
        }
    }
}
