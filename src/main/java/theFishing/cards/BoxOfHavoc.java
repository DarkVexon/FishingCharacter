package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.forAllMonstersLiving;

public class BoxOfHavoc extends AbstractFishingCard {
    public final static String ID = makeID("BoxOfHavoc");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 1

    public BoxOfHavoc() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseSecondMagic = secondMagic = 3;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isSolo()) {
            float duration = 0.25F;
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.RED.cpy(), ShockWaveEffect.ShockWaveType.CHAOTIC), duration));
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.YELLOW.cpy(), ShockWaveEffect.ShockWaveType.CHAOTIC), duration));
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.BLUE.cpy(), ShockWaveEffect.ShockWaveType.CHAOTIC), duration));
        }
        forAllMonstersLiving(q -> {
            applyToEnemy(q, new PoisonPower(q, p, secondMagic));
            if (p.hand.size() <= 1) {
                applyToEnemy(q, new WeakPower(q, magicNumber, false));
                applyToEnemy(q, new VulnerablePower(q, magicNumber, false));
            }
        });
    }

    public void triggerOnGlowCheck() {
        this.glowColor = isSolo() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeSecondMagic(2);
    }
}