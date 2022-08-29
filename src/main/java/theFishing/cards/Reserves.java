package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class Reserves extends AbstractFishingCard {
    public final static String ID = makeID("Reserves");
    // intellij stuff power, self, uncommon, , , , , , 

    public Reserves() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("ReservesPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (card.cost == -1) {
                    flash();
                    atb(new GainEnergyAction(amount));
                }
            }

            @Override
            public void updateDescription() {
                StringBuilder sb = new StringBuilder(cardStrings.EXTENDED_DESCRIPTION[1]);
                for (int i = 0; i < amount; i++) {
                    sb.append("[E] ");
                }
                sb.append(LocalizedStrings.PERIOD);
                description = sb.toString();
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}