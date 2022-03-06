package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ChocolateChipCookie extends AbstractFishingCard {
    public final static String ID = makeID("ChocolateChipCookie");
    // intellij stuff skill, self, rare, , , , , 6, 2

    public ChocolateChipCookie() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
        baseSecondMagic = secondMagic = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HealAction(p, p, magicNumber));
        applyToSelf(new DexterityPower(p, secondMagic));
        atb(new ArmamentsAction(true));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
    }
}