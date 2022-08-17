package theFishing.cards;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theFishing.patch.foil.FoilPatches;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class SicklyShine extends AbstractFishingCard {
    public final static String ID = makeID("SicklyShine");
    // intellij stuff power, self, rare, , , , , 1, 

    public SicklyShine() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("SicklyShinePower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (FoilPatches.isFoil(card)) {
                    flash();
                    forAllMonstersLiving(q -> applyToEnemy(q, new PoisonPower(q, owner, amount)));
                }
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + cardStrings.EXTENDED_DESCRIPTION[2];
            }
        });
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}