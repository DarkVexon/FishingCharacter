package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.SetSailAction;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class RodOfHope extends AbstractFishingCard {
    public final static String ID = makeID("RodOfHope");
    // intellij stuff skill, self, rare, , , , , , 

    public RodOfHope() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 4;
        AbstractCard q = new StarShard();
        FoilPatches.makeFoil(q);
        cardsToPreview = q;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (isVoyaged()) {
            AbstractCard q = new StarShard();
            FoilPatches.makeFoil(q);
            topDeck(q, magicNumber);
            exhaust = true;
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = isVoyaged() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}