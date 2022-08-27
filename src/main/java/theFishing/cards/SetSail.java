package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.actions.DeckToTopOfDeckAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class SetSail extends AbstractFishingCard {
    public final static String ID = makeID("SetSail");
    // intellij stuff skill, self, common, , , 4, , , 

    public SetSail() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DeckToTopOfDeckAction(p));
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new LoseStrengthPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}