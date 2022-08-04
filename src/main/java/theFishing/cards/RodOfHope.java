package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class RodOfHope extends AbstractFishingCard {
    public final static String ID = makeID("RodOfHope");
    // intellij stuff skill, self, rare, , , , , , 

    public RodOfHope() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);

        baseDamage = 5;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (isVoyaged()) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    att(new GainEnergyAction(Math.max(AbstractDungeon.player.energy.energyMaster - EnergyPanel.totalCount, 0)));
                }
            });
            atb(new DrawCardAction(magicNumber));
            exhaust = true;
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = isVoyaged() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}