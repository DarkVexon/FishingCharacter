package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class FirstClass extends AbstractFishingCard {
    public final static String ID = makeID("FirstClass");
    // intellij stuff power, self, uncommon, , , 6, 2, , 

    public FirstClass() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        AbstractCard q = new Flight();
        makeFoil(q);
        cardsToPreview = q;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("FirstClassPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void atStartOfTurn() {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    this.flash();
                    AbstractCard q = new Flight();
                    makeFoil(q);
                    this.addToBot(new MakeTempCardInHandAction(q, this.amount, false));
                }
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + (amount == 1 ? cardStrings.EXTENDED_DESCRIPTION[2] : cardStrings.EXTENDED_DESCRIPTION[3]);
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}