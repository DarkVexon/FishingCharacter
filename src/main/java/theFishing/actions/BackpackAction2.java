package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.List;

import static theFishing.FishingMod.makeID;

public class BackpackAction2 extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("BackpackAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    private final CardGroup targetGroup;

    public BackpackAction2(CardGroup targetGroup) {
        this.targetGroup = targetGroup;
        this.amount = 1;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (targetGroup.size() == 0) {
                this.isDone = true;
                return;
            }

            if (targetGroup.size() == 1) {
                moveThoseCardsToHand(targetGroup.group);
                this.isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(targetGroup, this.amount, false, TEXT[0]);
            this.tickDuration();
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            moveThoseCardsToHand(AbstractDungeon.gridSelectScreen.selectedCards);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }

    private static void moveThoseCardsToHand(List<AbstractCard> cards) {
        for (AbstractCard q : cards) {
            if (AbstractDungeon.player.drawPile.group.contains(q)) {
                AbstractDungeon.player.drawPile.moveToHand(q);
            }
        }
    }
}
