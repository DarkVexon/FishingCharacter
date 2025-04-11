package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Sinker extends AbstractFishingCard {
    public final static String ID = makeID(Sinker.class.getSimpleName());
    // intellij stuff skill, enemy, special, , , , , 1, 1

    public Sinker() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}