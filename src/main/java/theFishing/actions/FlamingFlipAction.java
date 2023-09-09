package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.FishingMod;
import theFishing.powers.AddCopyNextTurnPower;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static theFishing.util.Wiz.att;

public class FlamingFlipAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(FishingMod.makeID("FlamingFlipAction"));
    public static final String[] TEXT = uiStrings.TEXT;


    public FlamingFlipAction() {
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

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }

    private void mopUpSelectedCard(List<AbstractCard> cards) {
        att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AddCopyNextTurnPower(cards.get(0)), 1));
        AbstractDungeon.player.hand.moveToExhaustPile(cards.get(0));
    }
}
