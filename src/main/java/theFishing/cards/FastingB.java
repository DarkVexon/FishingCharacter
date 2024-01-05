package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.combat.FastingEffect;
import theFishing.powers.TakeItEasyPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class FastingB extends AbstractFishingCard {
    public final static String ID = makeID("FastingB");
    // intellij stuff power, self, uncommon, , , , , 4, 2

    public FastingB() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new FastingEffect(p.hb.cX, p.hb.cY, Color.ORANGE)));
        applyToSelf(new ThornsPower(p, magicNumber));
        applyToSelf(new MetallicizePower(p, magicNumber));
        applyToSelf(new TakeItEasyPower(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}