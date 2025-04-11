package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class GatherShards extends AbstractFishingCard {
    public final static String ID = makeID(GatherShards.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public GatherShards() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new TheEternityGem();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}