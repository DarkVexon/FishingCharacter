package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;

import static theFishing.util.Wiz.att;

public class ThortonsBank extends AbstractBoard {
    public static final String ID = FishingMod.makeID(ThortonsBank.class.getSimpleName());

    public ThortonsBank() {
        super(ID);
    }

    @Override
    public void effect() {
        att(new GainBlockAction(AbstractDungeon.player, 4));
    }

    @Override
    public void onObtainCard(AbstractCard card) {
        if (card.hasTag(FishingMod.DELVES)) {
            AbstractDungeon.player.gainGold(25);
        }
    }
}
