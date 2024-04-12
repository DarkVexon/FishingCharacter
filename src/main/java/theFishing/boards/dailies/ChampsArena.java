package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.powers.ArenaStackPower;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.att;

public class ChampsArena extends AbstractBoard {
    public static final String ID = FishingMod.makeID(ChampsArena.class.getSimpleName());

    public ChampsArena() {
        super(ID);
    }

    @Override
    public void effect() {
        if (AbstractDungeon.player.hasPower(ArenaStackPower.ID)) {
            applyToSelfTop(new StrengthPower(AbstractDungeon.player, 1));
            att(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ArenaStackPower.ID));
        } else {
            applyToSelfTop(new ArenaStackPower());
        }
    }

    @Override
    public void atRunStart() {
        AbstractDungeon.player.masterDeck.group.get(4).upgrade();
    }
}
