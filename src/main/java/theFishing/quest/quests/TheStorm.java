package theFishing.quest.quests;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class TheStorm extends AbstractQuest {

    public static final String ID = "TheStorm";

    public TheStorm() {
        super(ID, 6);
    }

    @Override
    public String getName() {
        return "Conduct Lightning";
    }

    @Override
    public String getDescription() {
        return "#yQuest: Spend #b" + goal + " Energy in a single turn. #yReward: A random enemy loses #b17 HP.";
    }

    @Override
    public void grantReward() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(true);
                att(new LoseHPAction(target, AbstractDungeon.player, 17));
            }
        });
    }

    @Override
    public void onSpendEnergy(int amount) {
        for (int i = 0; i < amount; i++) {
            increment();
        }
    }
}
