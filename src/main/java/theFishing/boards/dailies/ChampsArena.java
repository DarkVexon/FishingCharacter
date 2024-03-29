package theFishing.boards.dailies;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.makeInHandTop;

public class ChampsArena extends AbstractBoard {
    public static final String ID = FishingMod.makeID(ChampsArena.class.getSimpleName());

    public ChampsArena() {
        super(ID);
    }

    @Override
    public void effect() {
        applyToSelfTop(new LoseStrengthPower(AbstractDungeon.player, 2));
        applyToSelfTop(new StrengthPower(AbstractDungeon.player, 2));
    }

    @Override
    public void atRunStart() {
        AbstractDungeon.player.masterDeck.group.get(4).upgrade();
    }
}
