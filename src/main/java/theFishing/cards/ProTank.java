package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.cards.AbstractFishingCard;
import theFishing.powers.ProTankPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ProTank extends AbstractFishingCard {
    public final static String ID = makeID("ProTank");
    // intellij stuff power, self, uncommon, , , , , 3, 1

    public ProTank() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseDamage = 16;
        baseBlock = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new EnterTheDungeonAction());
    }

    public void upp() {
        upgradeDamage(4);
        upgradeBlock(4);
    }
}