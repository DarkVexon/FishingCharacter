package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

public class AcceptQuestAction extends AbstractGameAction {
    private AbstractQuest questToTake;

    public AcceptQuestAction(AbstractQuest questToTake) {
        this.questToTake = questToTake;
    }

    @Override
    public void update() {
        if (!QuestHelper.quests.stream().anyMatch(q -> q.questID.equals(questToTake.questID))) {
            QuestHelper.acceptQuest(questToTake);
        }
        else {
            addToTop(new DrawCardAction(1));
        }
        isDone = true;
    }
}
