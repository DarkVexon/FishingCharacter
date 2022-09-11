package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;

import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.util.Wiz.att;

public class ConfettiCannonFollowUpAction extends AbstractGameAction {
    private AbstractCard card;

    public ConfettiCannonFollowUpAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        isDone = true;
        if (DrawCardAction.drawnCards.stream().anyMatch(q -> FoilPatches.isFoil(q))) {
            addToTop(new PerfectPullAction(card));
        }
    }
}
