package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

public class AcceptQuestAction extends AbstractGameAction {
    private final AbstractQuest questToTake;

    public AcceptQuestAction(AbstractQuest questToTake) {
        this.questToTake = questToTake;
    }

    @Override
    public void update() {
        QuestHelper.acceptQuest(questToTake);
        isDone = true;
    }
}
