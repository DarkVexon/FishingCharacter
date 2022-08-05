package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.cards.AbstractFishingCard;
import theFishing.quest.quests.ThePrismaticPortal;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class QuestTimeThePrismaticPortal extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeThePrismaticPortal");
    // intellij stuff power, self, rare, , , , , , 

    public QuestTimeThePrismaticPortal() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new QuestTimeTheGemSearch();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AcceptQuestAction(new ThePrismaticPortal()));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}