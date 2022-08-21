package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class VexingDeal extends AbstractFishingCard {
    public final static String ID = makeID("VexingDeal");
    // intellij stuff power, self, rare, , , , , 33, 11

    public VexingDeal() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("VexingDealPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, 1) {

            @Override
            public void atStartOfTurnPostDraw() {
                addToBot(new DrawCardAction(amount));
                addToBot(new ExhaustAction(amount, false, false));
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + (amount == 1 ? cardStrings.EXTENDED_DESCRIPTION[2] : cardStrings.EXTENDED_DESCRIPTION[3]) + cardStrings.EXTENDED_DESCRIPTION[4] + amount + (amount == 1 ? cardStrings.EXTENDED_DESCRIPTION[2] : cardStrings.EXTENDED_DESCRIPTION[3]) + LocalizedStrings.PERIOD;
            }
        });
        if (AbstractDungeon.ascensionLevel >= 10) {
            applyToSelf(new StrengthPower(p, magicNumber));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.ascensionLevel >= 10 ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}