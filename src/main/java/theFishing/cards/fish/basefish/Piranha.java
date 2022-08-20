package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Piranha extends AbstractFishCard {
    public final static String ID = makeID("Piranha");
    // intellij stuff atack, enemy, special, 5, 4, , , , 

    public Piranha() {
        super(ID, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);

    }
}