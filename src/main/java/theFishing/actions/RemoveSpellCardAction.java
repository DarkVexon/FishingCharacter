package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.FishingMod;
import theFishing.boards.dailies.WizvexTower;

public class RemoveSpellCardAction extends AbstractGameAction {
    private final AbstractCard card;

    public RemoveSpellCardAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        isDone = true;
        if (FishingMod.activeBoard instanceof WizvexTower)
            ((WizvexTower) FishingMod.activeBoard).removeSpellCard(card);
    }
}