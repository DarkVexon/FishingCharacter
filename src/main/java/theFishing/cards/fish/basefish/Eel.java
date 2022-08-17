package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Eel extends AbstractFishCard {
    public final static String ID = makeID("Eel");
    // intellij stuff skill, self, , , , , , 

    public Eel() {
        super(ID, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}