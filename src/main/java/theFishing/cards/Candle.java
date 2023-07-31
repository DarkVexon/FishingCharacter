package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EnterTheDungeonAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Candle extends AbstractFishingCard {
    public final static String ID = makeID(Candle.class.getSimpleName());
    // intellij stuff skill, self, special, , , , , , 

    public Candle() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseBlock = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new EnterTheDungeonAction());
    }

    public void upp() {
        upgradeBlock(2);
    }
}