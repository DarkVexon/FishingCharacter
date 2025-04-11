package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class CrystalCrash extends AbstractFishingCard {
    public final static String ID = makeID(CrystalCrash.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CrystalCrash() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        isMultiDamage = true;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new TheEternityGem();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
        uDesc();
    }
}