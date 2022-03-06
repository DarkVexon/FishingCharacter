package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class Baited extends AbstractFishingCard {
    public final static String ID = makeID("Baited");
    // intellij stuff attack, enemy, uncommon, 16, 2, , , 1, 1

    public Baited() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 16;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        makeInHand(AbstractFishCard.returnRandomFish());
        if (m.getIntentBaseDmg() > -1) {
            for (int i = 0; i < magicNumber; i++) {
                makeInHand(AbstractFishCard.returnRandomFish());
            }
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}