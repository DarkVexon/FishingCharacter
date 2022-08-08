package theFishing.quest.quests;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class TheStorm extends AbstractQuest {

    public static final String ID = makeID("TheStorm");

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public TheStorm() {
        super(ID, 6);
    }

    @Override
    public String getName() {
        return uiStrings.TEXT[0];
    }

    @Override
    public String getDescription() {
        return uiStrings.TEXT[1] + goal + uiStrings.TEXT[2];
    }

    @Override
    public void grantReward() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(true);
                att(new LoseHPAction(target, AbstractDungeon.player, 17));
                att(new VFXAction(new LightningEffect(target.drawX, target.drawY), 0.05F));
                att(new SFXAction("ORB_LIGHTNING_EVOKE"));
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

    @Override
    public void atEndOfTurn() {
        progress = 0;
    }

    @Override
    public float textpadding() {
        return 120F;
    }
}
