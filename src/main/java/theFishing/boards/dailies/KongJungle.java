package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.cards.Banana;

import static theFishing.util.Wiz.att;
import static theFishing.util.Wiz.makeInHandTop;

public class KongJungle extends AbstractBoard {
    public static final String ID = FishingMod.makeID(KongJungle.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public KongJungle() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> makeInHandTop(new Banana())));
        effects.add(new BoardEffect(TEXT[1], () -> makeInHandTop(new Banana())));
        effects.add(new BoardEffect(TEXT[2], () -> att(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(20, true), DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE))));
    }
}
