package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class SupplyTrip extends AbstractFishingCard {
    public final static String ID = makeID("SupplyTrip");
    // intellij stuff skill, self, uncommon, , , , , 5, 1

    public SupplyTrip() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                int toDraw = this.amount - AbstractDungeon.player.hand.size();
                if (toDraw > 0) {
                    for (int i = 0; i < toDraw; i++) {
                        att(new GainBlockAction(p, block));
                        att(new DrawCardAction(1));
                    }
                }

                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}