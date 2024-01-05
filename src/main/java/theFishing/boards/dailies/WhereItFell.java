package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cards.StarShard;

import static theFishing.util.Wiz.att;
import static theFishing.util.Wiz.makeInHandTop;

public class WhereItFell extends AbstractBoard {
    public static final String ID = FishingMod.makeID(WhereItFell.class.getSimpleName());

    public WhereItFell() {
        super(ID);
    }

    @Override
    public void atRunStart() {
        AbstractDungeon.player.relics.remove(0);
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH/2F, Settings.HEIGHT/2F, AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON));
    }

    @Override
    public void effect() {
        makeInHandTop(new StarShard());
    }
}
