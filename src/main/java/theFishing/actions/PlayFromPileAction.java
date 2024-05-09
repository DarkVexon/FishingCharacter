package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.FishingMod;
import theFishing.TheFishing;
import theFishing.util.FishingAchievementUnlocker;
import theFishing.util.Wiz;

public class PlayFromPileAction extends AbstractGameAction {

    private final AbstractCard toPlay;
    private final CardGroup from;

    public PlayFromPileAction(AbstractCard toPlay, CardGroup from) {
        this.toPlay = toPlay;
        this.from = from;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractMonster targetFoe = Wiz.getRandomEnemy();
            from.group.remove(toPlay);
            AbstractDungeon.getCurrRoom().souls.remove(toPlay);
            AbstractDungeon.player.limbo.group.add(toPlay);
            toPlay.current_y = -200.0F * Settings.scale;
            toPlay.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            toPlay.target_y = (float) Settings.HEIGHT / 2.0F;
            toPlay.targetAngle = 0.0F;
            toPlay.lighten(false);
            toPlay.drawScale = 0.12F;
            toPlay.targetDrawScale = 0.75F;
            toPlay.applyPowers();
            this.addToTop(new NewQueueCardAction(toPlay, targetFoe, false, true));
            this.addToTop(new UnlimboAction(toPlay));
            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }

            if (toPlay.rarity == AbstractCard.CardRarity.RARE) {
                AbstractPlayer p = AbstractDungeon.player;
                if (p != null && p instanceof TheFishing) {
                    FishingAchievementUnlocker.unlockAchievement(FishingMod.makeID("STARLIGHT"));
                }
            }

            this.isDone = true;
        }

    }
}