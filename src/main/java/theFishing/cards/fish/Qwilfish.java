package theFishing.cards.fish;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Qwilfish extends AbstractFishCard {
    public final static String ID = makeID(Qwilfish.class.getSimpleName());
    // intellij stuff atack, enemy, special, 5, 4, , , ,

    public Qwilfish() {
        super(ID, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ThornsPower(p, magicNumber));
    }
}