package theFishing.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import theFishing.effects.MiniUpgradeShine;
import theFishing.util.Wiz;

import java.util.ArrayList;

public class FutureProofingAction extends AbstractGameAction {
    private static float DEST_X = -1.0F;
    private static float DEST_Y = -1.0F;

    public FutureProofingAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        boolean upgradedAnyCard = false;
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
                upgradedAnyCard = true;
            }
        }
        if (upgradedAnyCard) {
            if (DEST_X == -1.0F && DEST_Y == -1.0F) {
                Hitbox hb = ReflectionHacks.getPrivate(AbstractDungeon.overlayMenu.combatDeckPanel, DrawPilePanel.class, "hb");
                DEST_X = hb.cX;
                DEST_Y = hb.cY;
            }
            AbstractDungeon.topLevelEffects.add(new MiniUpgradeShine(DEST_X, DEST_Y));
        }
    }
}
