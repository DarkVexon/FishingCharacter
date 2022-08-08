package theFishing.cards.boxtoppers;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

@AutoAdd.Ignore
public class CardboardBox extends AbstractBoxTopper {
    public final static String ID = makeID("CardboardBox");
    // intellij stuff power, self, , , , , 1, 

    public CardboardBox() {
        super(ID, 1, CardType.POWER, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IntangiblePlayerPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}