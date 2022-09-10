package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.effects.RainbowExplosionEffect;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.Wiz;

public class PerfectPullAction extends AbstractGameAction {
    private final AbstractCard c;

    public PerfectPullAction(AbstractCard c) {
        this.c = c;
        this.source = AbstractDungeon.player;
    }

    @Override
    public void update() {
        isDone = true;
        Wiz.att(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (DrawCardAction.drawnCards.stream().anyMatch(q -> FoilPatches.isFoil(q))) {
                    addToTop(new PerfectPullAction(c));
                }
            }
        }));
        addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster tar = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                if (tar != null) {
                    c.calculateCardDamage(tar);
                    this.addToTop(new DamageAction(tar, new DamageInfo(AbstractDungeon.player, c.damage, c.damageTypeForTurn), AttackEffect.NONE));
                    addToTop(new VFXAction(new RainbowExplosionEffect(tar.hb.cX, tar.hb.cY)));
                }
                this.isDone = true;
            }
        });
    }
}
