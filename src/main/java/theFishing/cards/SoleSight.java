package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class SoleSight extends AbstractFishingCard {
    public final static String ID = makeID("SoleSight");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public SoleSight() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ScryAction(magicNumber));
        if (isSolo()) {
            atb(new VFXAction(p, new InflameEffect(p), 0.66F));
            applyToSelf(new StrengthPower(p, magicNumber));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = isSolo() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}