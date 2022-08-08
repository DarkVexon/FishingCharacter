package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class TheBackpack extends AbstractFishingCard {
    public final static String ID = makeID("TheBackpack");
    // intellij stuff skill, self, rare, , , , , , 

    public TheBackpack() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        isInnate = true;
        selfRetain = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> possCards = new ArrayList<>();
        for (AbstractCard q : p.drawPile.group) {
            if (FoilPatches.isFoil(q))
                possCards.add(q);
        }
        for (AbstractCard q : p.discardPile.group) {
            if (FoilPatches.isFoil(q))
                possCards.add(q);
        }
        Collections.sort(possCards, Comparator.comparing(c -> c.rarity));
        Collections.reverse(possCards);
        atb(new SelectCardsAction(possCards, 1, "Choose a card to put into your hand.", (cards) -> {
            AbstractCard card = cards.get(0);
            if (AbstractDungeon.player.drawPile.group.contains(card)) {
                AbstractDungeon.player.drawPile.moveToHand(card);
            } else if (AbstractDungeon.player.discardPile.group.contains(card)) {
                AbstractDungeon.player.discardPile.moveToHand(card);
            }
        }));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}