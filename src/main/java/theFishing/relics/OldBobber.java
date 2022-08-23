package theFishing.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class OldBobber extends AbstractEasyRelic {
    public static final String ID = makeID("OldBobber");

    public OldBobber() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atTurnStart() {
        counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractFishCard) {
            ++this.counter;
            if (this.counter % 3 == 0) {
                this.counter = 0;
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                atb(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(7, true, false), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
        }

    }

    @Override
    public void onVictory() {
        counter = -1;
    }
}
