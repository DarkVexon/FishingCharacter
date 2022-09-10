package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.powers.TimeWarpPower;
import theFishing.FishingMod;
import theFishing.cards.Preclude;
import theFishing.util.Wiz;

import static theFishing.util.Wiz.att;

public class PrecludeAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(FishingMod.makeID("PrecludeAction"));
    public static final String[] TEXT = uiStrings.TEXT;

    @Override
    public void update() {
        isDone = true;
        for (AbstractMonster q : Wiz.getEnemies()) {
            if (q.hasPower(TimeWarpPower.POWER_ID)) {
                q.getPower(TimeWarpPower.POWER_ID).amount = 0;
                q.getPower(TimeWarpPower.POWER_ID).flash();
                if (q instanceof TimeEater && AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(card -> card.cardID.equals(Preclude.ID)).count() == 1) {
                    att(new TalkAction(q, TEXT[0]));
                }
            }
        }
    }
}
