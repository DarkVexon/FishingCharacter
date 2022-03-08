package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.NoWhammiesFollowUpAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class NoWhammies extends AbstractFishingCard {
    public final static String ID = makeID("NoWhammies");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public NoWhammies() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber, new NoWhammiesFollowUpAction()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}