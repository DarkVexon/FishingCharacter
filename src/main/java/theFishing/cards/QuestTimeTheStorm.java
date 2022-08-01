package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theFishing.actions.AcceptQuestAction;
import theFishing.quest.quests.TheStorm;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class QuestTimeTheStorm extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheStorm");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public QuestTimeTheStorm() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnergizedPower(p, magicNumber));
        atb(new AcceptQuestAction(new TheStorm()));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}