package theFishing.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;

public class BackpackAction extends SelectCardsAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("BackpackAction"));

    public BackpackAction(ArrayList<AbstractCard> possCards) {
        super(possCards, 1, uiStrings.TEXT[0], (cards) -> {
            AbstractCard card = cards.get(0);
            if (AbstractDungeon.player.drawPile.group.contains(card)) {
                AbstractDungeon.player.drawPile.moveToHand(card);
            }
        });
    }
}
