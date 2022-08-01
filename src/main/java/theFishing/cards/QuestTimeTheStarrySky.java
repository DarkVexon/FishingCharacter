package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.cards.AbstractFishingCard;
import theFishing.quest.quests.TheStarrySky;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class QuestTimeTheStarrySky extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheStarrySky");
    // intellij stuff attack, all_enemy, uncommon, 7, 3, , , , 

    public QuestTimeTheStarrySky() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new AcceptQuestAction(new TheStarrySky()));
    }

    public void upp() {
        upgradeDamage(3);
    }
}