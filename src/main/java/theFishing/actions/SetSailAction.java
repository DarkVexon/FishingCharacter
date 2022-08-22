package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.util.Wiz;

import java.util.ArrayList;

public class SetSailAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final CardType typeToCheck;

    public SetSailAction(CardType typeToCheck) {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.typeToCheck = typeToCheck;
    }

    public void update() {
        if (this.p.drawPile.isEmpty()) {
            this.isDone = true;
            return;
        }
        ArrayList<AbstractCard> open = new ArrayList<>();
        for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
            if (q.type.equals(typeToCheck) && !(q instanceof AbstractFishCard)) {
                open.add(q);
            }
        }

        if (open.size() == 0) {
            this.isDone = true;
            return;
        }


        if (!open.isEmpty()) {
            AbstractCard card = Wiz.getRandomItem(open);
            if (this.p.hand.size() == 10) {
                this.p.createHandIsFullDialog();
            } else {
                p.drawPile.group.remove(card);
                p.drawPile.addToTop(card);
                addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        card.setCostForTurn(0);
                        card.superFlash();
                    }
                });
                this.addToTop(new DrawCardAction(1));
            }
        }

        this.isDone = true;
    }
}
