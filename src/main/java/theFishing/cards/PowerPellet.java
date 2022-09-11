package theFishing.cards;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.PowerPelletPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class PowerPellet extends AbstractFishingCard {
    public final static String ID = makeID("PowerPellet");
    // intellij stuff skill, self, special, , , , , 6, 3

    public PowerPellet() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 6;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction(makeID("WAKA_WAKA")));
        applyToSelf(new PowerPelletPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}