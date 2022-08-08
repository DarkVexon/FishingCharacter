package theFishing.cards.boxtoppers;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AbandonQuestAction;
import theFishing.cards.AbstractFishingCard;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

@AutoAdd.Ignore
public class TheKeyReturns extends AbstractBoxTopper {
    public final static String ID = makeID("TheKeyReturns");
    // intellij stuff attack, all_enemy, 11, 2, , , , 

    public TheKeyReturns() {
        super(ID, 2, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 11;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        for (AbstractQuest q : QuestHelper.quests) {
            q.grantReward();
            atb(new AbandonQuestAction(q));
        }
    }

    public void upp() {
        upgradeDamage(2);
    }
}