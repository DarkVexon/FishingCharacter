package theFishing.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.TheFishing;
import theFishing.cards.fish.basefish.Piranha;

import static theFishing.FishingMod.makeID;

public class UnageeSkull extends AbstractEasyRelic {
    public static final String ID = makeID("UnageeSkull");

    public UnageeSkull() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
       if (c.cardID.equals(Piranha.ID)) {
           return damage + 2;
       }
       return damage;
    }

    @Override
    public boolean canSpawn() {
        return false;
    }
}
