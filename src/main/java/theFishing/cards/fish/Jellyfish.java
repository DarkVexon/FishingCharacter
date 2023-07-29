package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Jellyfish extends AbstractFishCard {
    public final static String ID = makeID(Jellyfish.class.getSimpleName());
    // intellij stuff skill, self, , , , , ,

    public Jellyfish() {
        super(ID, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            atb(new UpgradeRandomCardAction());
    }
}