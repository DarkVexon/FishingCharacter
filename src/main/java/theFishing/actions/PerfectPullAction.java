package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theFishing.patch.FoilPatches;
import theFishing.util.Wiz;

public class PerfectPullAction extends AbstractGameAction {
    private int[] damage;
    private DamageInfo.DamageType type;
    private AttackEffect effect;
    private boolean isFast;

    public PerfectPullAction(AbstractCreature source, int[] amount, AbstractGameAction.AttackEffect effect, boolean isFast) {

        this.source = source;
        this.damage = amount;
        this.type = DamageInfo.DamageType.NORMAL;
        this.effect = effect;
        this.isFast = isFast;
    }

    @Override
    public void update() {
        Wiz.att(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (DrawCardAction.drawnCards.stream().anyMatch(q -> FoilPatches.isFoil(q))) {
                    addToTop(new PerfectPullAction(source, damage, effect, isFast));
                }
            }
        }));
        Wiz.att(new DamageAllEnemiesAction(source, damage, type, effect, isFast));
    }
}
