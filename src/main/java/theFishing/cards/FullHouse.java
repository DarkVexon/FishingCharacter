package theFishing.cards;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.att;

public class FullHouse extends AbstractFishingCard {
    public final static String ID = makeID("FullHouse");
    // intellij stuff power, self, rare, , , , , , 

    public FullHouse() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    @Override
    protected Texture getPortraitImage() {
        if (upgraded) {
            return ImageMaster.loadImage("fishingResources/images/cards/FourOfAKind_p.png");
        }
        return super.getPortraitImage();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(cardStrings.EXTENDED_DESCRIPTION[1], (cards) -> {
            for (AbstractCard q : cards) {
                AbstractCard tar = q.makeStatEquivalentCopy();
                if (!isFoil(tar)) {
                    makeFoil(tar);
                }
                att(new MakeTempCardInDrawPileAction(tar, magicNumber, true, true));
            }
        }));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            ++timesUpgraded;
            upgraded = true;
            name = cardStrings.EXTENDED_DESCRIPTION[0];
            initializeTitle();
            upp();
            loadCardImage("fishingResources/images/cards/FourOfAKind.png");
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}