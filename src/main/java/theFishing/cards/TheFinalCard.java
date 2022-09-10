package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import theFishing.actions.FinalCardAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.adp;
import static theFishing.util.Wiz.atb;

public class TheFinalCard extends AbstractFishingCard {
    public final static String ID = makeID("TheFinalCard");
    // intellij stuff attack, all_enemy, rare, 32, 8, , , , 

    public TheFinalCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 22;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isSolo()) {
            atb(new VFXAction(new GrandFinalEffect(), Settings.FAST_MODE ? 0.7F : 1.0F));
            atb(new FinalCardAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY, this));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = isSolo() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(6);
        uDesc();
    }
}