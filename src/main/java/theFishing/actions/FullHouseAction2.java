package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.List;

import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.att;

public class FullHouseAction2 extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileToTopOfDeckAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private CardGroup targetGroup;
    private int dupes;

    public FullHouseAction2(CardGroup targetGroup, int dupes) {
        this.targetGroup = targetGroup;
        this.dupes = dupes;
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
                duplicate(targetGroup.group, dupes);
                this.isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(targetGroup, this.amount, false, TEXT[0]);
            this.tickDuration();
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            duplicate(AbstractDungeon.gridSelectScreen.selectedCards, dupes);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }

    private static void duplicate(List<AbstractCard> cards, int amount) {
        for (AbstractCard q : cards) {
            if (!isFoil(q)) {
                makeFoil(q);
            }
            att(new MakeTempCardInDrawPileAction(q, amount, true, true));
        }
    }
}
