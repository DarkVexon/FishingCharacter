package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class SoleSight extends AbstractFishingCard {
    public final static String ID = makeID("SoleSight");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public SoleSight() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ScryAction(magicNumber));
        if (p.hand.size() <= 1) {
            atb(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK, true)));
            atb(new VFXAction(p, new InflameEffect(p), 1.0F));
            applyToSelf(new StrengthPower(p, magicNumber));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = adp().hand.size() <= 1 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}