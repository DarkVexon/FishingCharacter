package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class GolemGuardian extends AbstractFishingCard {
    public final static String ID = makeID(GolemGuardian.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , 4, 2, 4, 2

    public GolemGuardian() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ModifyBlockAction(this.uuid, -magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}