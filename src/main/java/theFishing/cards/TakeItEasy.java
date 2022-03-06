package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.AbstractFishingCard;
import theFishing.powers.TakeItEasyPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class TakeItEasy extends AbstractFishingCard {
    public final static String ID = makeID("TakeItEasy");
    // intellij stuff power, self, uncommon, , , , , 4, 1

    public TakeItEasy() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, -magicNumber)));
        applyToSelf(new TakeItEasyPower());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}