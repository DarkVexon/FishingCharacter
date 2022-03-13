package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theFishing.FishingMod;
import theFishing.powers.DrawLessNextTurnPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class RodOfHope extends AbstractFishingCard {
    public final static String ID = makeID("RodOfHope");
    // intellij stuff skill, self, rare, , , , , , 

    public RodOfHope() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        tags.add(STAR_IN_ART);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawLessNextTurnPower(magicNumber));
        applyToSelf(new LambdaPower("De-Energized", AbstractPower.PowerType.DEBUFF, true, p, 1) {

            @Override
            public void onEnergyRecharge() {
                flash();
                AbstractDungeon.player.loseEnergy(amount);
                addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            }

            @Override
            public void updateDescription() {
                description = "Gain #b" + amount + " fewer [E] next turn.";
            }
        });
        this.addToBot(new VFXAction(new WhirlwindEffect(Color.YELLOW.cpy(), true)));
        this.addToBot(new SkipEnemiesTurnAction());
        this.addToBot(new PressEndTurnButtonAction());
    }

    public void upp() {
        upgradeMagicNumber(-1);
        uDesc();
    }
}