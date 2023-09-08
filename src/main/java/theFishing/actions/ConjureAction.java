package theFishing.actions;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.dailies.WizvexTower;
import theFishing.util.Wiz;

import java.util.ArrayList;

public class ConjureAction extends AbstractGameAction {


    private boolean choose;
    private boolean free;

    public ConjureAction(boolean choose, boolean free) {
        this.choose = choose;
        this.free = free;
    }

    @Override
    public void update() {
        if (FishingMod.activeBoard instanceof WizvexTower) {
            isDone = true;
            if (((WizvexTower) FishingMod.activeBoard).spellCards.isEmpty()) {
                addToTop(new DrawCardAction(1));
                addToTop(new RefreshSpellsAction());
            } else {
                if (!choose) {
                    AbstractCard tar = Wiz.getRandomItem(((WizvexTower) FishingMod.activeBoard).spellCards, AbstractDungeon.cardRandomRng).card.makeStatEquivalentCopy();
                    if (free) tar.updateCost(-999);
                    addToTop(new MakeTempCardInHandAction(tar));
                    addToTop(new RemoveSpellCardAction(tar));
                } else {
                    ArrayList<WizvexTower.CardRenderInfo> possCards = new ArrayList<>();
                    possCards.addAll(((WizvexTower) FishingMod.activeBoard).spellCards);
                    ArrayList<WizvexTower.CardRenderInfo> availableCards = new ArrayList<>();
                    while (!possCards.isEmpty()) {
                        availableCards.add(possCards.remove(AbstractDungeon.cardRandomRng.random(possCards.size() - 1)));
                    }
                    ArrayList<AbstractCard> actualChoices = new ArrayList<>();
                    availableCards.forEach(q -> actualChoices.add(q.card.makeStatEquivalentCopy()));
                    addToTop(new SelectCardsCenteredAction(actualChoices, "Choose a Spell to add into your hand.", (cards) -> {
                        AbstractCard q = cards.get(0);
                        if (free) q.updateCost(-999);
                        addToTop(new MakeTempCardInHandAction(q));
                        addToTop(new RemoveSpellCardAction(q));
                    })); // TODO: Loc
                }
            }
        }
    }
}
