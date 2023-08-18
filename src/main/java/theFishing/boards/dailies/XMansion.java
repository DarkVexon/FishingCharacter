package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cards.StrikeX;

import static theFishing.util.Wiz.att;
import static theFishing.util.Wiz.makeInHandTop;

public class XMansion extends AbstractBoard {
    public static final String ID = FishingMod.makeID(XMansion.class.getSimpleName());

    public XMansion() {
        super(ID);
        effects.add(() -> att(new GainBlockAction(AbstractDungeon.player, 3)));
        effects.add(() -> att(new GainEnergyAction(1)));
        effects.add(() -> makeInHandTop(new Miracle()));
    }

    @Override
    public void atRunStart() {
        AbstractDungeon.player.masterDeck.group.remove(4);
        AbstractDungeon.player.masterDeck.group.add(4, new StrikeX());
    }
}
