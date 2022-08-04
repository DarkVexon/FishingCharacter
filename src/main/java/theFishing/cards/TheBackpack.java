package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class TheBackpack extends AbstractFishingCard {
    public final static String ID = makeID("TheBackpack");
    // intellij stuff skill, self, rare, , , , , , 

    public TheBackpack() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        isInnate = true;
        selfRetain = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup newGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard q : p.drawPile.group) {
            if (FoilPatches.isFoil(q))
                newGroup.addToTop(q);
        }
        for (AbstractCard q : p.discardPile.group) {
            if (FoilPatches.isFoil(q))
                newGroup.addToTop(q);
        }
        atb(new FetchAction(newGroup));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}