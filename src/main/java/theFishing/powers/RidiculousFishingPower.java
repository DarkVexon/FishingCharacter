package theFishing.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.patch.PreDrawPatch;

import static theFishing.FishingMod.makeID;

public class RidiculousFishingPower extends AbstractAdventurerPower {
    public static String ID = makeID(RidiculousFishingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public RidiculousFishingPower(int amount) {
        super(ID, powerStrings.NAME, AbstractPower.PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (PreDrawPatch.DRAWN_DURING_TURN) {
            flash();
            addToBot(new DamageRandomEnemyAction(new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
