package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class FlamingFlip extends AbstractFishingCard {
    public final static String ID = makeID("FlamingFlip");
    // intellij stuff skill, self, uncommon, , , 5, 3, , 

    public FlamingFlip() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AnimateHopAction(p));
        blck();
        atb(new ExhaustAction(1, false, false, false));
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
        uDesc();
    }
}