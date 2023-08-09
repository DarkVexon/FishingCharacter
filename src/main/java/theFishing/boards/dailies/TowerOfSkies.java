package theFishing.boards.dailies;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;

import static theFishing.util.Wiz.att;

public class TowerOfSkies extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TowerOfSkies.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public TowerOfSkies() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[2], () -> att(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    if (c.canUpgrade()) {
                        c.upgrade();
                        c.superFlash(Color.GOLD.cpy());
                    }
                }
            }
        }))));
        effects.add(new BoardEffect(TEXT[2], () -> att(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    if (c.canUpgrade()) {
                        c.upgrade();
                        c.superFlash(Color.GOLD.cpy());
                    }
                }
            }
        }))));
        effects.add(new BoardEffect(TEXT[3], () -> att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                att(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
            }
        })));
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }
}
