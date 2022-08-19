package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.Wiz;

public class PerfectPullAction extends AbstractGameAction {
    private AttackEffect effect;
    private boolean isFast;
    private AbstractCard c;

    public PerfectPullAction(AbstractCard c, AbstractGameAction.AttackEffect effect, boolean isFast) {
        this.c = c;

        this.source = AbstractDungeon.player;
        this.effect = effect;
        this.isFast = isFast;
    }

    @Override
    public void update() {
        isDone = true;
        Wiz.att(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (DrawCardAction.drawnCards.stream().anyMatch(q -> FoilPatches.isFoil(q))) {
                    addToTop(new PerfectPullAction(c, effect, isFast));
                }
            }
        }));
        Wiz.att(new AttackDamageRandomEnemyAction(c, effect));
    }
}
