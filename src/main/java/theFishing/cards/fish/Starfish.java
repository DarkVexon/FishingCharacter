package theFishing.cards.fish;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Starfish extends AbstractFishCard {
    public final static String ID = makeID(Starfish.class.getSimpleName());
    // intellij stuff atack, enemy, special, 5, 4, , , ,

    public Starfish() {
        super(ID, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PlatedArmorPower(p, magicNumber));
    }
}