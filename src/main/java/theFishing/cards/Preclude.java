package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;
import theFishing.FishingMod;
import theFishing.actions.PrecludeAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Preclude extends AbstractFishingCard {
    public final static String ID = makeID("Preclude");
    // intellij stuff attack, enemy, uncommon, 15, 5, , , , 

    public Preclude() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new GoldenSlashEffect(m.hb.cX, m.hb.cY, true), Settings.FAST_MODE ? 0.0F : 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new DiscardAction(p, p, 1, false));
        atb(new PrecludeAction());
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p, m);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(5);
    }
}