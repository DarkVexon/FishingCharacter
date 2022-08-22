package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static theFishing.FishingMod.makeID;

public class DiscardPileSetupAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("DiscardPileSetupAction"));

    public DiscardPileSetupAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
        } else {
            if (this.duration == Settings.ACTION_DUR_FASTER) {
                if (AbstractDungeon.player.discardPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                if (AbstractDungeon.player.discardPile.size() == 1) {
                    AbstractCard tmp = AbstractDungeon.player.discardPile.getTopCard();
                    tmp.freeToPlayOnce = true;
                    AbstractDungeon.player.discardPile.removeCard(tmp);
                    AbstractDungeon.player.discardPile.moveToDeck(tmp, false);
                }

                if (AbstractDungeon.player.discardPile.group.size() > this.amount) {
                    AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.discardPile, 1, uiStrings.TEXT[0], false, false, false, false);
                    this.tickDuration();
                    return;
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {

                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    AbstractDungeon.player.discardPile.removeCard(c);
                    if (c.cost > 0)
                        c.freeToPlayOnce = true;
                    AbstractDungeon.player.hand.moveToDeck(c, false);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }
}
