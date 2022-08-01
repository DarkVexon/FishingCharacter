package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.AbstractFishingCard;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.StarHelper.isStarCard;
import static theFishing.util.Wiz.*;

public class ShineBrighter extends AbstractFishingCard {
    public final static String ID = makeID("ShineBrighter");
    // intellij stuff skill, self, rare, , , , , , 

    public ShineBrighter() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Starlit Shine", AbstractPower.PowerType.BUFF, true, p, 1) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (isStarCard(card)) {
                    flash();
                    applyToSelf(new StrengthPower(owner, amount));
                    forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, -amount)));
                }
            }

            @Override
            public void atEndOfTurn(boolean isPlayer) {
                atb(new RemoveSpecificPowerAction(owner, owner, this));
            }

            @Override
            public void updateDescription() {
                description = "Whenever you play a card with a star in its art this turn, gain #b" + amount + " #yStrength and all enemies lose #b" + amount + " #yStrength.";
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}