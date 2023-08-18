package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EnterTheDungeonAction;

import static theFishing.FishingMod.DELVES;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class ProTank extends AbstractFishingCard {
    public final static String ID = makeID("ProTank");
    // intellij stuff power, self, uncommon, , , , , 3, 1

    public ProTank() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseDamage = 12;
        baseBlock = 10;
        tags.add(DELVES);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new EnterTheDungeonAction());
        atb(new EnterTheDungeonAction());
    }

    public void upp() {
        upgradeDamage(6);
        upgradeBlock(3);
    }
}