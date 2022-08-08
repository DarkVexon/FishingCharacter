package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.patch.PreDrawPatch;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Bobber extends AbstractFishingCard {
    public final static String ID = makeID("Bobber");
    // intellij stuff skill, none, uncommon, 9, 1, 12, 3, , 

    public Bobber() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseSecondMagic = secondMagic = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerWhenDrawn() {
        if (PreDrawPatch.DRAWN_DURING_TURN) {
            applyToSelfTop(new StrengthPower(AbstractDungeon.player, magicNumber));
            att(new GainBlockAction(AbstractDungeon.player, secondMagic));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeSecondMagic(3);
    }
}