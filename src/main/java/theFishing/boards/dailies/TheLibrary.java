package theFishing.boards.dailies;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.cardmods.StickerRetainMod;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class TheLibrary extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheLibrary.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public TheLibrary() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> att(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    CardModifierManager.addModifier(c, new StickerRetainMod());
                    c.superFlash(Color.GREEN.cpy());
                }
            }
        }))));
        effects.add(new BoardEffect(TEXT[1], () -> att(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    CardModifierManager.addModifier(c, new StickerRetainMod());
                    c.superFlash(Color.GREEN.cpy());
                }
            }
        }))));
        effects.add(new BoardEffect(TEXT[2], () -> att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int x = AbstractDungeon.player.hand.size();
                for (int i = 0; i < x; i++) {
                    att(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 3, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
                }
            }
        })));
    }
}
