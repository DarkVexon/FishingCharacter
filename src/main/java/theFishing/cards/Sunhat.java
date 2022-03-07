package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Sunhat extends AbstractFishingCard {
    public final static String ID = makeID("Sunhat");
    // intellij stuff skill, self, common, , , 7, 3, , 

    public Sunhat() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new RemoveSpecificPowerAction(p, p, WeakPower.POWER_ID));
    }

    public void upp() {
        upgradeBlock(3);
    }
}