package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class ThornyPersonality extends AbstractFishingCard {
    public final static String ID = makeID("ThornyPersonality");
    // intellij stuff skill, self, uncommon, , , 8, 2, 2, 1

    public ThornyPersonality() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (isVoyaged()) {
            applyToSelf(new ThornsPower(p, magicNumber));
        }
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}