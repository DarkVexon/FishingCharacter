package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class AquaOrb extends AbstractFishingCard {
    public final static String ID = makeID("AquaOrb");
    // intellij stuff skill, self, , , , 17, 6, , 

    public AquaOrb() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        forAllMonstersLiving(q -> applyToEnemy(q, new WeakPower(q, 1, false)));
        if (p.hand.size() == 1) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    att(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
                }
            });
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = adp().hand.size() == 1 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}