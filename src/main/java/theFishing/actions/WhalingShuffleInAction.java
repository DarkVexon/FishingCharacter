package theFishing.actions;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import theFishing.cards.Whaling;
import theFishing.effects.WhalingShowCardEffect;

public class WhalingShuffleInAction extends AbstractGameAction {
    private AbstractCard cardToMake;
    private boolean randomSpot;
    private boolean autoPosition;
    private boolean toBottom;
    private float x;
    private float y;

    public WhalingShuffleInAction(AbstractCard card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom, float cardX, float cardY) {
        UnlockTracker.markCardAsSeen(card.cardID);
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
        this.cardToMake = card;
        this.randomSpot = randomSpot;
        this.autoPosition = autoPosition;
        this.toBottom = toBottom;
        this.x = cardX;
        this.y = cardY;
    }

    public WhalingShuffleInAction(AbstractCard card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom) {
        this(card, amount, randomSpot, autoPosition, toBottom, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F);
    }

    public WhalingShuffleInAction(AbstractCard card, int amount, boolean shuffleInto, boolean autoPosition) {
        this(card, amount, shuffleInto, autoPosition, false);
    }

    public void update() {
        if (this.duration == this.startDuration) {
            AbstractCard c;
            int i;
            if (this.amount < 6) {
                for(i = 0; i < this.amount; ++i) {
                    c = this.cardToMake.makeStatEquivalentCopy();
                    c.cost += 1;
                    c.costForTurn = c.cost;
                    c.baseMagicNumber += 1;
                    c.magicNumber = c.baseMagicNumber;
                    c.cardsToPreview.cost = c.cost + 1;
                    c.cardsToPreview.costForTurn = c.cardsToPreview.cost;
                    c.cardsToPreview.baseMagicNumber = c.baseMagicNumber + 1;
                    c.cardsToPreview.magicNumber = c.cardsToPreview.baseMagicNumber;
                    if (c.type != CardType.CURSE && c.type != CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
                        c.upgrade();
                    }

                    AbstractDungeon.effectList.add(new WhalingShowCardEffect(c, this.x, this.y, this.randomSpot, this.autoPosition, this.toBottom));
                }
            } else {
                for(i = 0; i < this.amount; ++i) {
                    c = this.cardToMake.makeStatEquivalentCopy();
                    c.cost += 1;
                    c.costForTurn = c.cost;
                    c.baseMagicNumber += 1;
                    c.magicNumber = c.baseMagicNumber;
                    c.cardsToPreview.cost = c.cost + 1;
                    c.cardsToPreview.costForTurn = c.cardsToPreview.cost;
                    c.cardsToPreview.baseMagicNumber = c.baseMagicNumber + 1;
                    c.cardsToPreview.magicNumber = c.cardsToPreview.baseMagicNumber;
                    if (c.type != CardType.CURSE && c.type != CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
                        c.upgrade();
                    }

                    AbstractDungeon.effectList.add(new WhalingShowCardEffect(c, this.randomSpot, this.toBottom));
                }
            }

            this.duration -= Gdx.graphics.getDeltaTime();
        }

        this.tickDuration();
    }
}
