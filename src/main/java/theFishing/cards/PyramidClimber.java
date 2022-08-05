package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class PyramidClimber extends AbstractFishingCard {
    public final static String ID = makeID("PyramidClimber");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public PyramidClimber() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Pyramid Climb", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void atEndOfTurn(boolean isPlayer) {
                int x = AbstractDungeon.player.discardPile.size() / 6;
                if (x > 0) flash();
                for (int i = 0; i < x; i++) {
                    atb(new GainBlockAction(owner, amount));
                }
            }

            @Override
            public void updateDescription() {
                description = "At the end of your turn, gain #b" + amount + " #yBlock for every #b6 cards in your discard pile.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}