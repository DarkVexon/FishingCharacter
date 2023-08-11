package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.cards.StrikeX;

import static theFishing.util.Wiz.att;
import static theFishing.util.Wiz.makeInHandTop;

public class XMansion extends AbstractBoard {
    public static final String ID = FishingMod.makeID(XMansion.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public XMansion() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[2], () -> att(new GainBlockAction(AbstractDungeon.player, 3))));
        effects.add(new BoardEffect(TEXT[3], () -> att(new GainEnergyAction(1))));
        effects.add(new BoardEffect(TEXT[4], () -> makeInHandTop(new Miracle())));
    }

    @Override
    public void atRunStart() {
        AbstractDungeon.player.masterDeck.group.remove(5);
        AbstractDungeon.player.masterDeck.group.add(5, new StrikeX());
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }
}
