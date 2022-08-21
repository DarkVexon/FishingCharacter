package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Hammerhead extends AbstractFishCard {
    public final static String ID = makeID("Hammerhead");
    // intellij stuff skill, self, special, , , , , 1, 1

    public Hammerhead() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
    }
}