package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.TheGemSearch;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class QuestTimeTheGemSearch extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheGemSearch");
    // intellij stuff power, self, special, , , , , , 

    public QuestTimeTheGemSearch() {
        super(ID, 1, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        cardsToPreview = new TheEternityGem();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AcceptQuestAction(new TheGemSearch()));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}