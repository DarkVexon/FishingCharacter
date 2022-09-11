package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EternityGemAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class TheEternityGem extends AbstractFishingCard {
    public final static String ID = makeID("TheEternityGem");
    // intellij stuff attack, all_enemy, special, 13, 1, , , 13, 1

    public TheEternityGem() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        isEthereal = true;
        baseDamage = 11;
        baseMagicNumber = magicNumber = 11;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new EternityGemAction(this));
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}