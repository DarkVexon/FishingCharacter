package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class DeckedOut extends AbstractFishingCard {
    public final static String ID = makeID("DeckedOut");
    // intellij stuff skill, self, uncommon, , , 3, 1, 3, 1

    public DeckedOut() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        if (CardCrawlGame.clientUtils.isSteamRunningOnSteamDeck()) {
            atb(new VFXAction(new LightBulbEffect(p.hb)));
            atb(new GainEnergyAction(1));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}