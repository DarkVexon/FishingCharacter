package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
        cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        applyToSelf(new LambdaPower("Shiny Shiv", AbstractPower.PowerType.BUFF, true, p, 1) {
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
                description = "Whenever you play a #yFoil card this turn, add #b" + amount + " " + (amount == 1 ? "#yShiv" : "#yShivs") + " into your hand.";
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}