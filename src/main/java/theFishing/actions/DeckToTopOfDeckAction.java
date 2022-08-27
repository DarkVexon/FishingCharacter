package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class DeckToTopOfDeckAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileToTopOfDeckAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public DeckToTopOfDeckAction(AbstractCreature source) {
        this.setValues(null, source, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            tmp.addToRandomSpot(c);
        }
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            if (tmp.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (tmp.size() == 1) {
                AbstractCard tar = tmp.getTopCard();
                tmp.removeCard(tar);
                tmp.moveToDeck(tar, false);
            }

            if (tmp.group.size() > this.amount) {

                AbstractDungeon.gridSelectScreen.open(tmp, 1, TEXT[0], false, false, false, false);
                this.tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                tmp.removeCard(c);
                AbstractDungeon.player.hand.moveToDeck(c, false);
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }

        this.tickDuration();
    }
}
