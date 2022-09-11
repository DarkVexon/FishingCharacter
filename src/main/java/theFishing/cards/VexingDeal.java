package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.powers.VexingDealPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class VexingDeal extends AbstractFishingCard {
    public final static String ID = makeID("VexingDeal");
    // intellij stuff power, self, rare, , , , , 33, 11

    public VexingDeal() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VexingDealPower());
        if (AbstractDungeon.ascensionLevel >= 10) {
            applyToSelf(new StrengthPower(p, magicNumber));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.ascensionLevel >= 10 ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}