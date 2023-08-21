package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GiantEyeEffect;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.vfx;

public class GazeOfTheGods extends AbstractFishingCard {
    public final static String ID = makeID("GazeOfTheGods");
    // intellij stuff skill, enemy, rare, , , , , 30, 10

    public GazeOfTheGods() {
        super(ID, 4, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 30;
    }

    private static String scream() {
        int roll = MathUtils.random(1);
        if (roll == 0) {
            return "VO_NEMESIS_2A";
        } else {
            return "VO_NEMESIS_2B";
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction(scream()));
        vfx(new GiantEyeEffect(p.hb.cX, p.hb.cY + 300.0F * Settings.scale, new Color(1.0F, 0.4F, 0.8F, 0.0F)));
        vfx(new ThirdEyeEffect(m.hb.cX, m.hb.cY));
        atb(new LoseHPAction(m, p, magicNumber));
    }

    @Override
    public void triggerOnExhaust() {
        atb(new ReduceCostAction(this));
        atb(new ExhaustToHandAction(this));
    }

    public void upp() {
        upgradeMagicNumber(10);
    }
}