package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.BackpackAction;
import theFishing.actions.BackpackAction2;
import theFishing.patch.foil.FoilPatches;

import java.util.ArrayList;
import java.util.Collections;

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
        CardGroup possCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard q : p.drawPile.group) {
            if (FoilPatches.isFoil(q))
                possCards.addToRandomSpot(q);
        }
        atb(new BackpackAction2(possCards));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}