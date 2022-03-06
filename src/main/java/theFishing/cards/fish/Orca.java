package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Orca extends AbstractFishCard {
    public final static String ID = makeID("Orca");
    // intellij stuff skill, none, special, , , , , , 

    public Orca() {
        super(ID, 0, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE, AbstractCard.CardColor.COLORLESS);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            atb(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
    }

    @Override
    public void applyPowers() {
        if (magicNumber > 1) {
            if (!upgraded)
                rawDescription = String.format(cardStrings.EXTENDED_DESCRIPTION[0], magicNumber);
            else
                rawDescription = String.format(cardStrings.EXTENDED_DESCRIPTION[1], magicNumber);
        }
        super.initializeDescription();
    }

    public void upp() {
        selfRetain = true;
        uDesc();
    }
}