package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Eel extends AbstractFishCard {
    public final static String ID = makeID("Eel");
    // intellij stuff skill, self, , , , , , 

    public Eel() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}