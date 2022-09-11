package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.List;

public class WanderAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileToTopOfDeckAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public WanderAction() {
        this.amount = 1;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        CardGroup drawPile = AbstractDungeon.player.drawPile;
        if (this.duration == this.startDuration) {
            if (drawPile.size() == 0) {
                this.isDone = true;
                return;
            }

            if (drawPile.size() == 1) {
                selectedCardsToTopOfDeck(drawPile.group);
                this.isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(drawPile, this.amount, false, TEXT[0]);
            this.tickDuration();
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            selectedCardsToTopOfDeck(AbstractDungeon.gridSelectScreen.selectedCards);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }

    private static void selectedCardsToTopOfDeck(List<AbstractCard> cards) {
        for (AbstractCard q : cards) {
            AbstractDungeon.player.drawPile.removeCard(q);
            AbstractDungeon.player.drawPile.addToTop(q);
        }
    }
}
