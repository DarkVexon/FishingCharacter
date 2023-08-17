package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;

import static theFishing.util.Wiz.att;

public class TheStarship extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheStarship.class.getSimpleName());

    private AbstractCard thisCombatCard = null;

    public TheStarship() {
        super(ID);
        effects.add(() -> att(new UpgradeRandomCardAction()));
        effects.add(() -> att(new GainEnergyAction(1)));
        effects.add(() -> att(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 9, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE)));
    }
}
