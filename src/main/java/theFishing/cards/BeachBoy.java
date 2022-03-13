package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.deprecated.DEPRECATEDSublimeSlice;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorStakeEffect;
import theFishing.powers.SnaggedPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.atb;

public class BeachBoy extends AbstractFishingCard {
    public final static String ID = makeID("BeachBoy");
    // intellij stuff attack, enemy, attack, 5, 1, , , 5, 1

    public BeachBoy() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 5;
        isInnate = true;
        selfRetain = true;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new CollectorStakeEffect(m.hb.cX, m.hb.cY), 1F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new SnaggedPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}