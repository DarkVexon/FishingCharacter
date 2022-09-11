package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class BrokenOar extends AbstractAdventurerRelic {
    public static final String ID = makeID("BrokenOar");

    public BrokenOar() {
        super(ID, RelicTier.BOSS, LandingSound.HEAVY, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInDrawPileAction(new Shiv(), 4, true, true));
    }
}
