package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.FishingMod;
import theFishing.actions.AbandonQuestAction;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class KeyToTheCity extends AbstractFishingCard {
    public final static String ID = makeID("KeyToTheCity");
    // intellij stuff attack, all_enemy, rare, 10, 4, , , , 

    public KeyToTheCity() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 9;
        isMultiDamage = true;
        tags.add(FishingMod.DELVES);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new EnterTheDungeonAction());
        atb(new EnterTheDungeonAction());
        atb(new EnterTheDungeonAction());
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractQuest q : QuestHelper.quests) {
                    q.grantReward();
                    atb(new AbandonQuestAction(q));
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}