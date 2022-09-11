package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.effects.RainbowExplosionEffect;

public class ConfettiCannonDamageAction extends AbstractGameAction {
    private AbstractCard card;

    public ConfettiCannonDamageAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        isDone = true;
        AbstractMonster tar = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (tar != null) {
            card.calculateCardDamage(tar);
            this.addToTop(new DamageAction(tar, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn), AttackEffect.NONE));
            addToTop(new VFXAction(new RainbowExplosionEffect(tar.hb.cX, tar.hb.cY)));
        }
        this.isDone = true;
    }
}
