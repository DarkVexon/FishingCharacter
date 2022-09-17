package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.patch.foil.FoilPatches;

import static theFishing.util.Wiz.att;

public class ShinyShotsAction extends AbstractGameAction {
    public ShinyShotsAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        isDone = true;
        for (AbstractCard q : AbstractDungeon.player.hand.group) {
            if (FoilPatches.isFoil(q)) {
                att(new GainBlockAction(AbstractDungeon.player, amount));
            }
        }
    }
}
