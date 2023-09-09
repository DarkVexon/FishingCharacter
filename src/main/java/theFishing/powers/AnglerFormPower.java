package theFishing.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static theFishing.FishingMod.makeID;

public class AnglerFormPower extends AbstractAdventurerPower implements PreEnemyAttackPower {
    public static String ID = makeID(AnglerFormPower.class.getSimpleName());

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public AnglerFormPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }

    @Override
    public void preEnemyAttack(DamageInfo info) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                flash();
            }
        });
        addToBot(new WaitAction(0.15F));
        addToBot(new VFXAction(new BiteEffect(info.owner.hb.cX, info.owner.hb.cY, Color.YELLOW.cpy())));
        addToBot(new DamageAction(info.owner, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS)));
    }
}