package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class PyramidClimber extends AbstractFishingCard {
    public final static String ID = makeID(PyramidClimber.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 8, 3, , 

    public PyramidClimber() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new NextTurnBlockPower(p, block, this.originalName));
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = isSolo() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public boolean freeToPlay() {
        if (Wiz.isInCombat() && isSolo()) {
            return true;
        }
        return super.freeToPlay();
    }
}