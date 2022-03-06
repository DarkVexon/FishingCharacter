package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class SetSail extends AbstractFishingCard {
    public final static String ID = makeID("SetSail");
    // intellij stuff skill, self, common, , , 4, , , 

    public SetSail() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        forAllMonstersLiving((q) -> {
            applyToEnemy(q, new WeakPower(q, 1, false));
        });
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void upp() {
    }
}