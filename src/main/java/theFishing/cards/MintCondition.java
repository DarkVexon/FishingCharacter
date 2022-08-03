package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.patch.FoilPatches;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class MintCondition extends AbstractFishingCard {
    public final static String ID = makeID("MintCondition");
    // intellij stuff self, , uncommon, , , , , 2, 1

    public MintCondition() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Mint Condition", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void onCardDraw(AbstractCard card) {
                if (FoilPatches.isFoil(card)) {
                    flashWithoutSound();
                    applyToSelf(new StrengthPower(owner, amount));
                    applyToSelf(new LoseStrengthPower(owner, amount));
                }
            }

            @Override
            public void updateDescription() {
                description = "Whenever you draw a #yFoil card, gain #b" + amount + " #yTemporary Strength.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}