package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class RodOfHope extends AbstractFishingCard {
    public final static String ID = makeID(RodOfHope.class.getSimpleName());
    // intellij stuff attack, self_and_enemy, , 15, 2, 15, 2, ,

    public RodOfHope() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        if (isVoyaged() || isSolo()) {
            addToBot(new DrawCardAction(1));
            addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = (isVoyaged() || isSolo()) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(3);
    }
}