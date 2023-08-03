package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;

import static theFishing.util.Wiz.atb;

public class MorshusShop extends AbstractBoard {
    public static final String ID = FishingMod.makeID(MorshusShop.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public MorshusShop() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[2], () -> atb(new ScryAction(3))));
        effects.add(new BoardEffect(TEXT[2], () -> {
            atb(new DrawCardAction(2));
            atb(new ExhaustAction(1, false));
        }));
        effects.add(new BoardEffect(TEXT[3], () -> atb(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(7, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE))));
    }

    @Override
    public void atRunStart() {
        AbstractDungeon.player.gainGold(25);
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }
}
