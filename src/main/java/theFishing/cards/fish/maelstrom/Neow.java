package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Neow extends AbstractFishCard {
    public final static String ID = makeID("Neow");
    // intellij stuff skill, enemy, , , , , 50, 10

    public Neow() {
        super(ID, 3, CardType.SKILL, CardTarget.SELF_AND_ENEMY);
        baseMagicNumber = magicNumber = 50;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new LoseHPAction(m, p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(16);
    }
}