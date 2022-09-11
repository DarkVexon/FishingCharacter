package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.FishingMod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static theFishing.util.Wiz.att;

public class MopUpAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(FishingMod.makeID("MopUpAction"));
    public static final String[] TEXT = uiStrings.TEXT;


    public MopUpAction() {
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        ArrayList<AbstractCard> hand = AbstractDungeon.player.hand.group;
        if (this.duration == this.startDuration) {
            if (hand.size() != 0) {
                if (hand.size() == 1) {
                    mopUpSelectedCard(hand.stream().collect(Collectors.toList()));

                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                    this.isDone = true;
                } else {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            mopUpSelectedCard(AbstractDungeon.handCardSelectScreen.selectedCards.group);

            hand.addAll(AbstractDungeon.handCardSelectScreen.selectedCards.group);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }

    private static void mopUpSelectedCard(List<AbstractCard> cards) {
        if (cards.get(0).type == AbstractCard.CardType.STATUS || cards
                .get(0).type == AbstractCard.CardType.CURSE || cards
                .get(0).color == AbstractCard.CardColor.CURSE) {
            att(new DrawCardAction(1));
        }
        att(new ExhaustSpecificCardAction(cards.get(0), AbstractDungeon.player.hand));
    }
}
