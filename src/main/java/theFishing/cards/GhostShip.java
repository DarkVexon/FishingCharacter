package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;

public class GhostShip extends AbstractFishingCard implements StartOfTurnInExhaustCard {
    public final static String ID = makeID("GhostShip");
    // intellij stuff attack, enemy, uncommon, 6, 3, , , 6, 3

    public GhostShip() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public void atTurnStartInExhaust() {
        addToBot(new AllEnemyLoseHPAction(magicNumber, AbstractGameAction.AttackEffect.FIRE));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(3);
    }
}