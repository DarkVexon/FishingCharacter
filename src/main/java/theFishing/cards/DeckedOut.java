package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;
import theFishing.patch.foil.FoilShiny;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class DeckedOut extends AbstractFishingCard {
    public final static String ID = makeID("AllHandsOnDeck");
    // intellij stuff skill, self, uncommon, , , , , 3, 1

    public DeckedOut() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        AbstractCard q = new Shiv();
        FoilPatches.makeFoil(q);
        cardsToPreview = q;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Shiv();
        FoilPatches.makeFoil(q);
        if (FoilShiny.FoilCardsShine.isOnSteamDeck()) {
            q.upgrade();
        }
        makeInHand(q, magicNumber);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = FoilShiny.FoilCardsShine.isOnSteamDeck() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}