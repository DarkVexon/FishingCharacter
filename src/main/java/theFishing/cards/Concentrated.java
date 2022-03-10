package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Concentrated extends AbstractFishingCard {
    public final static String ID = makeID("Concentrated");
    // intellij stuff power, self, rare, , , , , 2, -1

    public Concentrated() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BerserkPower(p, 1));
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}