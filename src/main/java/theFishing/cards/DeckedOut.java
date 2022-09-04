package theFishing.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import theFishing.patch.foil.FoilShiny;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class DeckedOut extends AbstractFishingCard {
    public final static String ID = makeID("DeckedOut");
    // intellij stuff skill, self, uncommon, , , 3, 1, 3, 1

    public DeckedOut() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        AbstractCard q = new Shiv();
        AbstractCard q2 = new Smite();
        makeFoil(q2);
        MultiCardPreview.add(this, q, q2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Shiv();
        AbstractCard q2 = new Smite();
        makeFoil(q2);
        if (FoilShiny.FoilCardsShine.isOnSteamDeck()) {
            q.upgrade();
            q2.upgrade();
            atb(new VFXAction(new LightBulbEffect(p.hb)));
        }
        makeInHand(q, magicNumber);
        makeInHand(q2);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = FoilShiny.FoilCardsShine.isOnSteamDeck() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}