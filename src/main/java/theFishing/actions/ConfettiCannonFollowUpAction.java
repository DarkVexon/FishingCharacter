package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.patch.foil.FoilPatches;

public class ConfettiCannonFollowUpAction extends AbstractGameAction {
    private final AbstractCard card;

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
