package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
        effects.add(new BoardEffect(TEXT[2], () -> {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    att(new DamageAction(Wiz.getFrontmostEnemy(), new DamageInfo(AbstractDungeon.player, 5, DamageInfo.DamageType.THORNS), AttackEffect.BLUNT_LIGHT));
                }
            });
            if (AbstractDungeon.player.gold >= 200) {
                AbstractDungeon.player.loseGold(2);
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        att(new DamageAction(Wiz.getFrontmostEnemy(), new DamageInfo(AbstractDungeon.player, 5, DamageInfo.DamageType.THORNS), AttackEffect.BLUNT_LIGHT));
                    }
                });
            }
        }));

        effects.add(new BoardEffect(TEXT[3], () -> {
            atb(new GainBlockAction(AbstractDungeon.player, 4));
            if (AbstractDungeon.player.gold >= 200) {
                AbstractDungeon.player.loseGold(2);
                atb(new GainBlockAction(AbstractDungeon.player, 4));
            }
        }));

        effects.add(new BoardEffect(TEXT[2], () -> {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractMonster tar = Wiz.getFrontmostEnemy();
                    att(new ApplyPowerAction(tar, AbstractDungeon.player, new VulnerablePower(tar, 1, false)));
                    att(new ApplyPowerAction(tar, AbstractDungeon.player, new WeakPower(tar, 1, false)));
                }
            });
            if (AbstractDungeon.player.gold >= 200) {
                AbstractDungeon.player.loseGold(2);
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        AbstractMonster tar = Wiz.getFrontmostEnemy();
                        att(new ApplyPowerAction(tar, AbstractDungeon.player, new VulnerablePower(tar, 1, false)));
                        att(new ApplyPowerAction(tar, AbstractDungeon.player, new WeakPower(tar, 1, false)));
                    }
                });
            }
        }));
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }
}
