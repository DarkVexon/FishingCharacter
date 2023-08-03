package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.util.Wiz;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class ThortonsBank extends AbstractBoard {
    public static final String ID = FishingMod.makeID(ThortonsBank.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public ThortonsBank() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[2], () -> atb(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 5, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT))));
        effects.add(new BoardEffect(TEXT[3], () -> atb(new GainBlockAction(AbstractDungeon.player, 4))));
        effects.add(new BoardEffect(TEXT[4], () -> atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster tar = Wiz.getRandomEnemy();
                att(new ApplyPowerAction(tar, AbstractDungeon.player, new VulnerablePower(tar, 1, false)));
                att(new ApplyPowerAction(tar, AbstractDungeon.player, new WeakPower(tar, 1, false)));
            }
        })));
    }

    @Override
    public void onObtainCard(AbstractCard card) {
        if (card.hasTag(FishingMod.DELVES)) {
            AbstractDungeon.player.gainGold(25);
        }
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }
}
