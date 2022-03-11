package theFishing.cards;

import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.cards.AbstractFishingCard;
import theFishing.quest.quests.TheSlimyPath;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.shuffleIn;

public class QuestTimeTheSlimyPath extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheSlimyPath");
    // intellij stuff power, self, rare, , , , , , 

    public QuestTimeTheSlimyPath() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new Slimed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        shuffleIn(new Slimed(), 3);
        atb(new AcceptQuestAction(new TheSlimyPath()));
    }

    public void upp() {
        uDesc();
    }
}