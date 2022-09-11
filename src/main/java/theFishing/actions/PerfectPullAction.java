package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.util.Wiz;

public class PerfectPullAction extends AbstractGameAction {
    private final AbstractCard c;

    public PerfectPullAction(AbstractCard c) {
        this.c = c;
        this.source = AbstractDungeon.player;
    }

    @Override
    public void update() {
        isDone = true;
        Wiz.att(new DrawCardAction(1, new ConfettiCannonFollowUpAction(c)));
        addToTop(new ConfettiCannonDamageAction(c));
    }
}
