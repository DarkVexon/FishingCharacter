package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theFishing.util.Wiz.att;

public class NostalgicStrikeFirstAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        if (!AbstractDungeon.player.discardPile.isEmpty()) {
            AbstractCard card = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
            att(new NostalgicStrikeSecondAction(card));
        }
    }
}
