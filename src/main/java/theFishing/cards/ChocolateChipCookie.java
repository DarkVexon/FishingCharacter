package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class ChocolateChipCookie extends AbstractFishingCard {
    public final static String ID = makeID("ChocolateChipCookie");
    // intellij stuff skill, self, rare, , , , , 6, 2

    public ChocolateChipCookie() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 1;
        exhaust = true;
        tags.add(STAR_IN_ART);
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO: Crunch munch
        atb(new HealAction(p, p, magicNumber));
        applyToSelf(new DexterityPower(p, secondMagic));
        atb(new ArmamentsAction(true));
        atb(new TalkAction(true, "mmm...", 1F, 2F));
    }

    @Override
    public float getTitleFontSize() {
        return 19F;
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}