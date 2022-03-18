package theFishing.cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.screens.runHistory.RunHistoryScreen;
import com.megacrit.cardcrawl.screens.stats.RunData;
import theFishing.powers.VictoryLapPower;

import java.util.ArrayList;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class VictoryLap extends AbstractFishingCard {
    public final static String ID = makeID("VictoryLap");
    // intellij stuff power, self, uncommon, , , , , ,

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
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Miracle();
        baseMagicNumber = magicNumber = 1;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VictoryLapPower(1, upgraded));
        if (wonPreviousRun()) {
            applyToSelf(new StrengthPower(p, magicNumber));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = wonPreviousRun() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
        AbstractCard q = new Miracle();
        q.upgrade();
        cardsToPreview = q;
        uDesc();
    }
}