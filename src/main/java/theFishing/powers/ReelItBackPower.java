package theFishing.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFishing.actions.PlayFromPileAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class ReelItBackPower extends AbstractAdventurerPower {
    public static String ID = makeID(ReelItBackPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public ReelItBackPower() {
        super(ID, powerStrings.NAME, PowerType.BUFF, true, AbstractDungeon.player, 1);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (EnergyPanel.getCurrentEnergy() > 0) {
            flash();
            for (int i = 0; i < amount; i++) {
                if (EnergyPanel.getCurrentEnergy() > 0) {
                    EnergyPanel.useEnergy(1);
                    atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            if (!AbstractDungeon.player.discardPile.isEmpty()) {
                                AbstractCard target = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
                                att(new PlayFromPileAction(target, AbstractDungeon.player.discardPile));
                                isDone = true;
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? powerStrings.DESCRIPTIONS[0] : (powerStrings.DESCRIPTIONS[1] + amount + powerStrings.DESCRIPTIONS[2]);
    }
}
