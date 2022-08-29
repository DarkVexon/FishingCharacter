package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class FullySleeved extends AbstractFishingCard {
    public final static String ID = makeID("FullySleeved");
    // intellij stuff power, self, uncommon, , , 6, 2, , 

    public FullySleeved() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("FullySleevedPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(q -> isFoil(q)).count() >= 2) {
                    flash();
                    atb(new GainBlockAction(owner, amount));
                }
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + cardStrings.EXTENDED_DESCRIPTION[2];
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}