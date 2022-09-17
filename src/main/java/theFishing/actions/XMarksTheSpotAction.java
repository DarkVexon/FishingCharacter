package theFishing.actions;

import com.evacipated.cardcrawl.mod.stslib.patches.CenterGridCardSelectScreen;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.cards.blue.Tempest;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.att;

public class XMarksTheSpotAction extends AbstractGameAction {
    private final CardGroup selectGroup;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("XMarksTheSpotAction"));

    public XMarksTheSpotAction(ArrayList<AbstractCard> group) {
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
        this.selectGroup = new CardGroup(CardGroupType.UNSPECIFIED);
        this.selectGroup.group.addAll(group.stream().distinct().collect(Collectors.toList()));
    }

    public void update() {
        if (this.duration == this.startDuration) {
            CenterGridCardSelectScreen.centerGridSelect = true;
            AbstractDungeon.gridSelectScreen.open(this.selectGroup, 1, false, uiStrings.TEXT[0]);
            this.tickDuration();
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            CenterGridCardSelectScreen.centerGridSelect = false;
            if (AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID.equals(Tempest.ID) && AbstractDungeon.player.maxOrbs == 0) {
                att(new TalkAction(true, uiStrings.TEXT[1], 0.3F, 2F));
                att(new IncreaseMaxOrbAction(5));
            }
            att(new MakeTempCardInHandAction(AbstractDungeon.gridSelectScreen.selectedCards.get(0)));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }
}
