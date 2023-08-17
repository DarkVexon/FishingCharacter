package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.FocusPower;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;

import static theFishing.util.Wiz.*;

public class Circuitry extends AbstractBoard {
    public static final String ID = FishingMod.makeID(Circuitry.class.getSimpleName());

    public Circuitry() {
        super(ID);
        effects.add(() -> att(new ChannelAction(new Lightning())));
        effects.add(() -> att(new ChannelAction(new Frost())));
        effects.add(() -> applyToSelfTop(new FocusPower(AbstractDungeon.player, 2)));
    }

    @Override
    public void atBattleStartPreDraw() {
        int value = (int) AbstractDungeon.player.masterDeck.group.stream().filter(q -> q.hasTag(FishingMod.DELVES)).count();
        if (value > 0)
            atb(new IncreaseMaxOrbAction(value));
    }
}
