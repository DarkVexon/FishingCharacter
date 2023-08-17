package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;

import static theFishing.util.Wiz.att;

public class Termina extends AbstractBoard {
    public static final String ID = FishingMod.makeID(Termina.class.getSimpleName());

    public Termina() {
        super(ID);
        effects.add(() -> {
        });
        effects.add(() -> {
        });
        effects.add(() -> att(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(20, true), DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE)));
    }
}
