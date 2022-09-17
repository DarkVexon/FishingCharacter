package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.PerfectPullAction;

import static theFishing.FishingMod.makeID;

public class ConfettiCannon extends AbstractFishingCard {
    public final static String ID = makeID("ConfettiCannon");
    // intellij stuff attack, all_enemy, rare, 7, 3, , , , 

    public ConfettiCannon() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PerfectPullAction(this));
    }

    public void upp() {
        upgradeDamage(3);
    }
}