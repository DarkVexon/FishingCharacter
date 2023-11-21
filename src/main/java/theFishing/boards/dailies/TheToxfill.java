package theFishing.boards.dailies;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.SadisticPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.util.Wiz;

import static theFishing.util.Wiz.*;

public class TheToxfill extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheToxfill.class.getSimpleName());

    public TheToxfill() {
        super(ID);
        effects.add(() -> applyToSelfTop(new SadisticPower(AbstractDungeon.player, 2)));
        effects.add(() -> applyToSelfTop(new SadisticPower(AbstractDungeon.player, 2)));
        effects.add(() -> {
            for (int i = Wiz.getEnemies().size() - 1; i >= 0; i--) {
                applyToEnemyTop(Wiz.getEnemies().get(i), new StrengthPower(Wiz.getEnemies().get(i), -1));
            }
        });
    }

    @Override
    public void atBattleStartPreDraw() {
        applyToSelf(new SadisticPower(AbstractDungeon.player, 1));
    }
}
