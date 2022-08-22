package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.effects.RedPressurePoints;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Crosshairs extends AbstractFishingCard {
    public final static String ID = makeID("Crosshairs");
    // intellij stuff power, self, rare, , , , , 12, 3

    public Crosshairs() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("CrosshairsPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (card.cost == -1) {
                    flash();
                    int x = amount;
                    addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractMonster q = null;
                            for (AbstractMonster m2 : getEnemies()) {
                                q = m2;
                                break;
                            }
                            if (q != null) {
                                this.addToTop(new DamageAction(q, new DamageInfo(owner, x, DamageInfo.DamageType.THORNS), AttackEffect.NONE));
                                att(new VFXAction(new RedPressurePoints(q.hb.cX, q.hb.cY)));
                            }
                        }
                    });
                }
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + cardStrings.EXTENDED_DESCRIPTION[2];
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}