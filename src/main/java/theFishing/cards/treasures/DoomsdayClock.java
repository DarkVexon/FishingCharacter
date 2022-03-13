package theFishing.cards.treasures;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class DoomsdayClock extends AbstractTreasureCard {
    public final static String ID = makeID("DoomsdayClock");
    // intellij stuff skill, all_enemy, , , , , 6, 2

    public DoomsdayClock() {
        super(ID, 1, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 7;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(q -> {
            applyToEnemy(q, new WeakPower(q, magicNumber, false));
            applyToEnemy(q, new VulnerablePower(q, magicNumber, false));
        });
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}