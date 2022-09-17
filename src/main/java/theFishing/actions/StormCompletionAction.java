package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static theFishing.util.Wiz.att;

public class StormCompletionAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        AbstractMonster target = AbstractDungeon.getRandomMonster();
        att(new LoseHPAction(target, AbstractDungeon.player, 17));
        att(new VFXAction(new LightningEffect(target.drawX, target.drawY), 0.05F));
        att(new SFXAction("ORB_LIGHTNING_EVOKE"));
    }
}
