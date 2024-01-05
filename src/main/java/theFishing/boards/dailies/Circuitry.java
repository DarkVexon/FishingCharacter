package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class Circuitry extends AbstractBoard {
    public static final String ID = FishingMod.makeID(Circuitry.class.getSimpleName());

    public Circuitry() {
        super(ID);
    }

    @Override
    public void effect() {
        if (AbstractDungeon.cardRandomRng.randomBoolean()) {
            att(new ChannelAction(new Lightning()));
        } else {
            att(new ChannelAction(new Frost()));
        }
    }

    @Override
    public void atBattleStartPreDraw() {
        int value = (int) AbstractDungeon.player.masterDeck.group.stream().filter(q -> q.hasTag(FishingMod.DELVES)).count();
        if (value > 0)
            atb(new IncreaseMaxOrbAction(value));
    }
}
