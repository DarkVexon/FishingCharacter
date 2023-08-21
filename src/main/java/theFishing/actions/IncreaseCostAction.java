package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class IncreaseCostAction extends AbstractGameAction {
    UUID uuid;
    private AbstractCard card = null;

    public IncreaseCostAction(AbstractCard card) {
        this.card = card;
    }

    public IncreaseCostAction(UUID targetUUID) {
        this.uuid = targetUUID;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.card == null) {
            for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                c.modifyCostForCombat(1);
            }
        } else {
            this.card.modifyCostForCombat(1);
        }

        this.isDone = true;
    }
}
