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

import static theFishing.util.Wiz.att;

public class TowerOfSkies extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TowerOfSkies.class.getSimpleName());

    public TowerOfSkies() {
        super(ID);
        effects.add(() -> att(new DrawCardAction(1, new AbstractGameAction() {
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
        })));
        effects.add(() -> att(new DrawCardAction(1, new AbstractGameAction() {
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
        })));
        effects.add(() -> att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                att(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
            }
        }));
    }
}
