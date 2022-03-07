package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Lie extends AbstractFishingCard {
    public final static String ID = makeID("Lie");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 2

    public Lie() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Cheat();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_PIERCING_WAIL"));
        if (Settings.FAST_MODE) {
            atb(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.WHITE.cpy(), ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        } else {
            atb(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.WHITE.cpy(), ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
        }

        forAllMonstersLiving(q -> {
            applyToEnemy(q, new StrengthPower(q, -magicNumber));
            if (!q.hasPower(ArtifactPower.POWER_ID)) {
                applyToEnemy(q, new GainStrengthPower(q, magicNumber));
            }
        });

        AbstractCard q = new Cheat();
        if (upgraded) q.upgrade();
        makeInHand(q);
    }

    public void upp() {
        upgradeMagicNumber(2);
        AbstractCard q = new Cheat();
        q.upgrade();
        cardsToPreview = q;
        uDesc();
    }
}