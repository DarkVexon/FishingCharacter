package theFishing.cards.boxtoppers;

import basemod.AutoAdd;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

@AutoAdd.Ignore
public class Rebalance extends AbstractBoxTopper {
    public final static String ID = makeID("Rebalance");
    // intellij stuff skill, all, , , , , , 

    public Rebalance() {
        super(ID, 0, CardType.SKILL, CardTarget.ALL);
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, -1)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}