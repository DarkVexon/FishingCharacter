package theFishing.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.TheFishing;
import theFishing.cards.fish.basefish.Guppy;
import theFishing.cards.fish.basefish.Piranha;

import static theFishing.FishingMod.makeID;

public class UnageeSkull extends AbstractEasyRelic implements ModifyBlockRelic {
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
    public float modifyBlock(float block, AbstractCard source) {
        if (source.cardID.equals(Guppy.ID)) {
            return block + 2;
        }
        return block;
    }
}
