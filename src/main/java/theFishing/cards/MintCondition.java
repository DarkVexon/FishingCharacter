package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.patch.foil.FoilPatches;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class MintCondition extends AbstractFishingCard {
    public final static String ID = makeID("MintCondition");
    // intellij stuff self, , uncommon, , , , , 2, 1

    public MintCondition() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("MintConditionPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            private int playedThisTurn = 0;

            @Override
            public void atStartOfTurn() {
                playedThisTurn = 0;
            }

            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (FoilPatches.isFoil(card)) {
                    playedThisTurn += 1;
                    if (playedThisTurn == 2) {
                        flash();
                        atb(new GainBlockAction(owner, amount));
                    }
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