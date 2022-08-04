package theFishing.actions;

import com.badlogic.gdx.utils.Array;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.patch.foil.FoilPatches;

public class SetSailAction extends AbstractGameAction {
    private AbstractPlayer p;

    public SetSailAction() {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        isDone = true;
        if (this.p.drawPile.isEmpty()) {
            return;
        }

        Array<CardGroup> groups = new Array();
        for (int i = 0; i < 14; i++)
            groups.add(new CardGroup(CardGroupType.UNSPECIFIED));
        for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
            if (q.type == CardType.ATTACK) {
                if (q.rarity == AbstractCard.CardRarity.RARE && FoilPatches.isFoil(q)) {
                    groups.get(0).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.RARE) {
                    groups.get(1).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.UNCOMMON && FoilPatches.isFoil(q)) {
                    groups.get(2).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.UNCOMMON) {
                    groups.get(3).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.COMMON && FoilPatches.isFoil(q) && q.type != CardType.STATUS) {
                    groups.get(4).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.COMMON && q.type != CardType.STATUS) {
                    groups.get(5).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.BASIC && FoilPatches.isFoil(q)) {
                    groups.get(6).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.BASIC) {
                    groups.get(7).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.SPECIAL && FoilPatches.isFoil(q)) {
                    groups.get(8).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.SPECIAL) {
                    groups.get(9).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.CURSE && FoilPatches.isFoil(q)) {
                    groups.get(10).addToTop(q);
                } else if (q.rarity == AbstractCard.CardRarity.CURSE) {
                    groups.get(11).addToTop(q);
                } else if (q.type == CardType.STATUS && FoilPatches.isFoil(q)) {
                    groups.get(12).addToTop(q);
                } else {
                    groups.get(13).addToTop(q);
                }
            }
        }

        int group = 0;

        for (int i = 0; i < this.amount; ++i) {
            while (group < 14 && groups.get(group).isEmpty())
                group++;
            if (group < 14) {
                groups.get(group).shuffle();
                AbstractCard card = groups.get(group).getBottomCard();
                groups.get(group).removeCard(card);
                p.drawPile.removeCard(card);
                p.drawPile.addToTop(card);
                card.freeToPlayOnce = true;
                addToTop(new DrawCardAction(1));
            }
        }

        this.isDone = true;
    }
}
