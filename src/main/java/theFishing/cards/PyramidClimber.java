package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class PyramidClimber extends AbstractFishingCard {
    public final static String ID = makeID(PyramidClimber.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 8, 3, , 

    public PyramidClimber() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = isSolo() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public boolean freeToPlay() {
        if (isSolo()) {
            return true;
        }
        return super.freeToPlay();
    }
}