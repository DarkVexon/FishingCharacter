package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class AnchorsAweigh extends AbstractFishingCard implements StartupCard {
    public final static String ID = makeID("AnchorsAweigh");
    // intellij stuff skill, self, rare, , , 10, 5, , 

    public AnchorsAweigh() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 15;
        baseMagicNumber = magicNumber = 10;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public boolean atBattleStartPreDraw() {
        addToBot(new GainBlockAction(AbstractDungeon.player, magicNumber));
        atb(new GainEnergyAction(1));
        return true;
    }

    public void upp() {
        upgradeBlock(5);
        upgradeMagicNumber(5);
    }
}