package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

public class AbandonQuestAction extends AbstractGameAction {
    private final AbstractQuest takenQuest;

    public AbandonQuestAction(AbstractQuest takenQuest) {
        this.takenQuest = takenQuest;
    }

    @Override
    public void update() {
        isDone = true;
        QuestHelper.quests.remove(takenQuest);
    }
}
