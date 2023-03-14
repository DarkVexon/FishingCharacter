package theFishing.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class AnglerForm extends AbstractFishingCard {
    public final static String ID = makeID("AnglerForm");
    // intellij stuff power, self, rare, , , , , 3, 1

    public AnglerForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 6;
        selfRetain = true;
        tags.add(BaseModCardTags.FORM);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(3);
    }
}