package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeEffect;
import theFishing.cards.AbstractFishingCard;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Wizardry extends AbstractFishingCard {
    public final static String ID = makeID("Wizardry");
    // intellij stuff skill, self, uncommon, , , , , 5, 3

    public Wizardry() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        AbstractCard q = new Pinball();
        FoilPatches.makeFoil(q);
        cardsToPreview = q;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new ThirdEyeEffect(p.hb.cX, p.hb.cY)));
        applyToSelf(new VigorPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void triggerWhenDrawn() {
        AbstractCard q = new Pinball();
        FoilPatches.makeFoil(q);
        att(new MakeTempCardInDrawPileAction(q, 1, false, true));
    }
}