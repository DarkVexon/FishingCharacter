package theFishing.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.att;

public class FullHouseAction extends SelectCardsAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("FullHouseAction"));

    public FullHouseAction(ArrayList<AbstractCard> possCards, int magicNumber) {
        super(possCards, 1, uiStrings.TEXT[0], (cards) -> {
            AbstractCard q = cards.get(0).makeStatEquivalentCopy();
            if (!isFoil(q)) {
                makeFoil(q);
            }
            att(new MakeTempCardInDrawPileAction(q, magicNumber, true, true));
        });
    }
}
