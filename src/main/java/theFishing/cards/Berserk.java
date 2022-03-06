package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Berserk extends AbstractFishingCard {
    public final static String ID = makeID("Berserk");
    // intellij stuff power, self, rare, , , , , 2, -1

    public Berserk() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VulnerablePower(p, magicNumber, false));
        applyToSelf(new BerserkPower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(-1);
    }
}