package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.actions.SetSailAction;
import theFishing.powers.LambdaPower;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.att;

public class VexingDeal extends AbstractFishingCard {
    public final static String ID = makeID("VexingDeal");
    // intellij stuff power, self, rare, , , , , 33, 11

    public VexingDeal() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Vexing Deal", AbstractPower.PowerType.BUFF, false, p, 1) {

            @Override
            public void atStartOfTurnPostDraw() {
                int x = this.amount;
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        ArrayList<AbstractCard> valid = new ArrayList<>();
                        valid.addAll(AbstractDungeon.player.hand.group);
                        ArrayList<AbstractCard> toExhaust = new ArrayList<>();
                        for (int i = 0; i < x; i++) {
                            AbstractCard toHit = SetSailAction.getRarestCardInList(valid, null, false);
                            valid.remove(toHit);
                            toExhaust.add(toHit);
                        }
                        for (AbstractCard q : toExhaust) {
                            att(new DrawCardAction(2));
                            att(new ExhaustSpecificCardAction(q, AbstractDungeon.player.hand));
                        }
                        isDone = true;
                    }
                });
            }

            @Override
            public void updateDescription() {
                description = "At the start of your turn, #yExhaust #b" + amount + (amount == 1 ? "card" : "cards" ) + " random cards in your hand and draw #b" + amount * 2 + " cards.";
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}