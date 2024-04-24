package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;
import theFishing.actions.PrecludeAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Preclude extends AbstractFishingCard {
    public final static String ID = makeID("Preclude");
    // intellij stuff attack, enemy, uncommon, 15, 5, , , , 

    public Preclude() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new GoldenSlashEffect(m.hb.cX, m.hb.cY, false), Settings.FAST_MODE ? 0.0F : 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new VFXAction(new GoldenSlashEffect(m.hb.cX, m.hb.cY, false), Settings.FAST_MODE ? 0.0F : 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new DiscardAction(p, p, 1, false));
        atb(new PrecludeAction());
    }

    public void upp() {
        upgradeDamage(2);
    }
}
