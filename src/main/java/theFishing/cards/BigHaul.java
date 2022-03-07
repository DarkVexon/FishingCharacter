package theFishing.cards;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class BigHaul extends AbstractFishingCard {
    public final static String ID = makeID("BigHaul");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public BigHaul() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard q = AbstractFishCard.returnRandomFish();
            CardModifierManager.addModifier(q, new RetainMod());
            makeInHand(q);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}