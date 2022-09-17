package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.util.Wiz.att;

public class PerfectPullFollowUpAction extends AbstractGameAction {
    private final AbstractCard card;

    public PerfectPullFollowUpAction(AbstractCard card, AbstractMonster target) {
        this.card = card;
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.startDuration = 0.1F;
        this.duration = this.startDuration;
    }

    public void update() {
        isDone = true;
        if (DrawCardAction.drawnCards.stream().anyMatch(q -> isFoil(q))) {
            att(new DamageAction(target, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
        }
    }
}
