package theFishing.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class FinalCardAction extends AbstractGameAction {
    public int[] damage;

    private int baseDamage;

    private boolean firstFrame = true, utilizeBaseDamage = false;
    private AbstractCard toUp;

    public FinalCardAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect, AbstractCard toUp) {
        this.toUp = toUp;
        this.source = source;
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.firstFrame) {
            boolean playedMusic = false;
            int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
            if (this.utilizeBaseDamage)
                this.damage = DamageInfo.createDamageMatrix(this.baseDamage);
            for (int i = 0; i < temp; i++) {
                if (!(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).isDying &&
                        (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).currentHealth > 0 &&
                        !(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).isEscaping)
                    if (playedMusic) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cX,
                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cY, this.attackEffect, true));
                    } else {
                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cX,
                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cY, this.attackEffect));
                    }
            }
            this.firstFrame = false;
        }
        tickDuration();
        if (this.isDone) {
            for (AbstractPower p : AbstractDungeon.player.powers)
                p.onDamageAllEnemies(this.damage);
            int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
            for (int i = 0; i < temp; i++) {
                if (!(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).isDeadOrEscaped()) {
                    if (this.attackEffect == AbstractGameAction.AttackEffect.POISON) {
                        (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).tint.color.set(Color.CHARTREUSE);
                        (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).tint.changeColor(Color.WHITE.cpy());
                    } else if (this.attackEffect == AbstractGameAction.AttackEffect.FIRE) {
                        (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).tint.color.set(Color.RED);
                        (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).tint.changeColor(Color.WHITE.cpy());
                    }
                    (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).damage(new DamageInfo(this.source, this.damage[i], this.damageType));
                }
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
                AbstractCard q = StSLib.getMasterDeckEquivalent(toUp);
                if (q != null && !q.upgraded) {
                    q.upgrade();
                    float x = MathUtils.random(0.1F, 0.9F) * (float) Settings.WIDTH;
                    float y = MathUtils.random(0.2F, 0.8F) * (float) Settings.HEIGHT;
                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(q.makeStatEquivalentCopy(), x, y));
                    AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
                }
            }
            if (!Settings.FAST_MODE)
                addToTop(new WaitAction(0.1F));
        }
    }
}
