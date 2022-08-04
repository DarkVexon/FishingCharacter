package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class TheStorm extends AbstractQuest {

    public static final String ID = "TheStorm";

    public TheStorm() {
        super(ID, 6);
    }

    @Override
    public String getName() {
        return "The Storm";
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

    private static Texture incomplete = TexLoader.getTexture(makeImagePath("quests/Storm.png"));
    private static Texture complete = TexLoader.getTexture(makeImagePath("quests/Storm_Completed.png"));

    @Override
    public Texture progressTex(int idx) {
        if (progress > idx) {
            return complete;
        }
        return incomplete;
    }
}
