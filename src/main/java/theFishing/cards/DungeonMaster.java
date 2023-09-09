package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.DungeonMasterPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class DungeonMaster extends AbstractFishingCard {
    public final static String ID = makeID("DungeonMaster");
    // intellij stuff power, self, rare, , , , , 25, 7

    public DungeonMaster() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DungeonMasterPower(magicNumber));
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}