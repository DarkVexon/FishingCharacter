package theFishing.cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class RodSlam extends AbstractFishingCard {
    public final static String ID = makeID("RodSlam");
    // intellij stuff attack, enemy, 12, 3, , , , 

    public RodSlam() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        AbstractPower p2 = new DrawReductionPower(p, 1);
        ReflectionHacks.setPrivate(p2, DrawReductionPower.class, "justApplied", false);
        applyToSelf(p2);
    }

    public void upp() {
        upgradeDamage(3);
    }
}