package theFishing.relics;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class Newsletter extends AbstractAdventurerRelic {
    public static final String ID = makeID("Newsletter");

    public Newsletter() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheFishing.Enums.FISHING_COLOR);
    }

    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof ShopRoom) {
            this.flash();
            this.pulse = true;
        } else {
            this.pulse = false;
        }
    }

    public boolean canSpawn() {
        return (Settings.isEndless || AbstractDungeon.floorNum <= 48) && !(AbstractDungeon.getCurrRoom() instanceof ShopRoom);
    }

    public static final float SHOP_CARD_PRICE_REDUCE = 0.75F; // mult by
}
