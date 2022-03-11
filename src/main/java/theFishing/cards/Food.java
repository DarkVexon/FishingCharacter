package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Food extends AbstractFishingCard {
    public final static String ID = makeID("Food");
    // intellij stuff skill, self, special, , , , , , 

    public Food() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        selfRetain = true;
        exhaust = true;
    }

    public void setX(int amount) {
        this.magicNumber = amount;
        this.baseMagicNumber = this.magicNumber;
        StringBuilder sb = new StringBuilder("Retain. NL Gain ");
        for (int i = 0; i < amount; i++) {
            sb.append("[E] ");
        }
        sb.append(". NL Exhaust.");
        this.rawDescription = sb.toString();
        this.initializeDescription();
    }


    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        card.baseMagicNumber = this.baseMagicNumber;
        card.magicNumber = this.magicNumber;
        card.description = (ArrayList) this.description.clone();
        return card;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        if (upgraded) {
            atb(new DrawCardAction(1));
        }
    }

    public void upp() {
        rawDescription = rawDescription.replaceAll("Exhaust.", "Draw 1 card. NL Exhaust.");
        initializeDescription();
    }
}