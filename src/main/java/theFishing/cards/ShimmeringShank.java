package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.patch.foil.FoilPatches;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class ShimmeringShank extends AbstractFishingCard {
    public final static String ID = makeID("ShimmeringShank");
    // intellij stuff attack, enemy, uncommon, 4, 2, , , , 

    public ShimmeringShank() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
        cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new LambdaPower(makeID("ShinyShivPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, true, p, 1) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (FoilPatches.isFoil(card)) {
                    flash();
                    atb(new MakeTempCardInHandAction(new Shiv(), amount, true));
                }
            }

            @Override
            public void atEndOfTurn(boolean isPlayer) {
                atb(new RemoveSpecificPowerAction(owner, owner, this));
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + (amount == 1 ? cardStrings.EXTENDED_DESCRIPTION[2] : cardStrings.EXTENDED_DESCRIPTION[3]) + cardStrings.EXTENDED_DESCRIPTION[4];
            }
        });
    }

    public void upp() {
        upgradeBlock(3);
    }
}