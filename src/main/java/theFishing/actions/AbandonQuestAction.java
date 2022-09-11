package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theFishing.quest.QuestHelper;

public class AbandonQuestAction extends AbstractGameAction {
    private final String ID;

    public AbandonQuestAction(String ID) {
        this.ID = ID;
    }

    @Override
    public void update() {
        isDone = true;
        QuestHelper.quests.removeIf(q -> q.questID.equals(ID));
    }
}
