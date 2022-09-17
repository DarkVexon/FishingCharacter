package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class ExpulsionLetter extends AbstractAdventurerRelic {
    public static final String ID = makeID("ExpulsionLetter");

    public ExpulsionLetter() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    public void atBattleStartPreDraw() {
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractCard q = new Miracle();
        AbstractCard q2 = new Smite();
        AbstractCard q3 = new Safety();
        makeFoil(q);
        makeFoil(q2);
        makeFoil(q3);
        makeInHand(q);
        makeInHand(q2);
        makeInHand(q3);
    }

}
