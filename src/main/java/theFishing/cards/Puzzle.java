package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Puzzle extends AbstractFishingCard {
    public final static String ID = makeID("Puzzle");
    // intellij stuff skill, none, special, , , , , 1, 1

    public Puzzle() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new ExhaustAction(1, false));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}