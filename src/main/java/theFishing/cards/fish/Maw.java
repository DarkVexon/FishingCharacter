package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Maw extends AbstractFishCard {
    public final static String ID = makeID(Maw.class.getSimpleName());
    // intellij stuff atack, enemy, special, 5, 4, , , ,

    public Maw() {
        super(ID, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 1;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            atb(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY)));
            dmg(m, AbstractGameAction.AttackEffect.NONE);
        }
    }
}