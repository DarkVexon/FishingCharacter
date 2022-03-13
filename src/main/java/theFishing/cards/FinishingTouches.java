package theFishing.cards;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class FinishingTouches extends AbstractFishingCard {
    public final static String ID = makeID("FinishingTouches");
    // intellij stuff power, self, rare, , , , , , 

    public FinishingTouches() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Finishing Touches", AbstractPower.PowerType.BUFF, false, p, 1) {
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (!card.purgeOnUse && this.amount > 0 && AbstractDungeon.player.hand.size() == 1) {
                    this.flash();
                    AbstractMonster m = null;
                    if (action.target != null) {
                        m = (AbstractMonster) action.target;
                    }

                    AbstractCard tmp = card.makeSameInstanceOf();
                    AbstractDungeon.player.limbo.addToBottom(tmp);
                    tmp.current_x = card.current_x;
                    tmp.current_y = card.current_y;
                    tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                    tmp.target_y = (float) Settings.HEIGHT / 2.0F;
                    if (m != null) {
                        tmp.calculateCardDamage(m);
                    }

                    tmp.purgeOnUse = true;
                    AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
                }

            }

            @Override
            public void updateDescription() {
                description = amount == 1 ? "When you play a card, if it's the only one in your hand, play it twice." : ("When you play a card, if it's the only one in your hand, play it #b" + amount + " additional times.");
            }
        });
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}