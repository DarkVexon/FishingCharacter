package theFishing.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theFishing.util.Wiz;

import java.util.ArrayList;

public class FutureProofingAction extends AbstractGameAction {
    public FutureProofingAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        isDone = true;
        for (int i = 0; i < this.amount; i++) {
            ArrayList<AbstractCard> possCards = new ArrayList<>();
            for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
                if (q.canUpgrade()) {
                    possCards.add(q);
                }
            }
            if (!possCards.isEmpty()) {
                AbstractCard tar = Wiz.getRandomItem(possCards, AbstractDungeon.cardRandomRng);
                tar.upgrade();
                float x = MathUtils.random(0.1F, 0.9F) * (float) Settings.WIDTH;
                float y = MathUtils.random(0.2F, 0.8F) * (float) Settings.HEIGHT;
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(tar.makeStatEquivalentCopy(), x, y));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
            }
        }
    }
}
