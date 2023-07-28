package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;
import theFishing.powers.DungeonMasterPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class DungeonMaster extends AbstractFishingCard {
    public final static String ID = makeID("DungeonMaster");
    // intellij stuff power, self, rare, , , , , 25, 7

    public DungeonMaster() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DungeonMasterPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}