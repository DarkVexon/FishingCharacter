package theFishing.cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.runHistory.RunHistoryScreen;
import com.megacrit.cardcrawl.screens.stats.RunData;

import java.util.ArrayList;


import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class VictoryLap extends AbstractFishingCard {
    public final static String ID = makeID("VictoryLap");

    public static int wonPrevRun = -99;

    public static boolean wonPreviousRun() {
        if (wonPrevRun == -99) {
            RunHistoryScreen rhs = new RunHistoryScreen();
            rhs.refreshData();
            ArrayList<RunData> unfilteredRuns = ReflectionHacks.getPrivate(rhs, RunHistoryScreen.class, "unfilteredRuns");
            if (unfilteredRuns.isEmpty()) {
                wonPrevRun = 0;
            } else
                wonPrevRun = unfilteredRuns.get(unfilteredRuns.size() - 1).victory ? 1 : 0;
        }
        return wonPrevRun == 1 ? true : false;
    }

    public VictoryLap() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseBlock = 3;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        if (wonPreviousRun()) {
            blck();
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = wonPreviousRun() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeBlock(1);
    }
}