package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class ScavengedShield extends AbstractFishingCard {
    public final static String ID = makeID("ScavengedShield");
    // intellij stuff skill, self, common, , , 7, 2, , 

    public ScavengedShield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (isVoyaged()) {
            blck();
        }
    }

    public void upp() {
        upgradeBlock(2);
    }
}