package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class BurningStudy extends AbstractFishingCard {
    public final static String ID = makeID(BurningStudy.class.getSimpleName());
    // intellij stuff skill, self, special, , , , , 2, 1

    public BurningStudy() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustAction(1, false));
        vfx(new InflameEffect(p), 1.0F);
        applyToSelf(new StrengthPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}