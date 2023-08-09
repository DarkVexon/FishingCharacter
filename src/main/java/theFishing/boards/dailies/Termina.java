package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;

import static theFishing.util.Wiz.att;

public class Termina extends AbstractBoard {
    public static final String ID = FishingMod.makeID(Termina.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public Termina() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[2], () -> {
        }));
        effects.add(new BoardEffect(TEXT[2], () -> {
        }));
        effects.add(new BoardEffect(TEXT[3], () -> att(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(20, true), DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE))));
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }
}
