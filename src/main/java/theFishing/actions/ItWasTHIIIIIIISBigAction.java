package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;

public class ItWasTHIIIIIIISBigAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("SillyFishSizes"));

    public ItWasTHIIIIIIISBigAction(int amount) {
        this.amount = amount;
        duration = startDuration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            tripleCard(c);
            if (c instanceof AbstractFishCard) {
                c.superFlash();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            tripleCard(c);
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            tripleCard(c);
        }
        isDone = true;
    }

    private void tripleCard(AbstractCard c) {
        if (c instanceof AbstractFishCard) {
            if (c.baseDamage > 0)
                c.damage = c.baseDamage = c.baseDamage * amount;
            if (c.baseBlock > 0)
                c.block = c.baseBlock = c.baseBlock * amount;
            if (c.baseMagicNumber > 0)
                c.magicNumber = c.baseMagicNumber = c.baseMagicNumber * amount;
            if (((AbstractFishingCard) c).baseSecondMagic > 0)
                ((AbstractFishingCard) c).secondMagic = ((AbstractFishingCard) c).baseSecondMagic = ((AbstractFishingCard) c).baseSecondMagic * amount;
            c.applyPowers();
            increaseNameSize((AbstractFishCard) c);
        }
    }

    private static void increaseNameSize(AbstractFishCard c) {
        boolean upgraded = false;
        for (int i = 1; i < 6; i++) {
            if (c.name.startsWith(uiStrings.TEXT[i])) {
                c.name = c.name.replaceFirst(uiStrings.TEXT[i], uiStrings.TEXT[i - 1]);
                upgraded = true;
            }
        }
        if (!upgraded && !c.name.startsWith(uiStrings.TEXT[0])) {
            c.name = uiStrings.TEXT[5] + c.name;
        }
        c.trickyInitializeTitle();
    }
}
