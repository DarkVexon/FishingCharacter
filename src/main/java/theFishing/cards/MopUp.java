package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import theFishing.actions.MopUpAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class MopUp extends AbstractFishingCard {
    public final static String ID = makeID("MopUp");
    // intellij stuff attack, enemy, common, 7, 3, , , , 

    public MopUp() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_WHIFF_2", 0.3F));
        atb(new SFXAction("ATTACK_FAST", 0.2F));
        atb(new VFXAction(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, 200.0F, 290.0F, 3.0F, Color.FOREST, Color.GREEN)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new MopUpAction(m, damage, damageTypeForTurn));
    }

    public void upp() {
        upgradeDamage(2);
    }
}
