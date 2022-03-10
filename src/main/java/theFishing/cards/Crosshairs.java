package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Crosshairs extends AbstractFishingCard {
    public final static String ID = makeID("Crosshairs");
    // intellij stuff power, self, rare, , , , , 12, 3

    public Crosshairs() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Crosshairs", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            public void onAfterCardPlayed(AbstractCard card) {
                if (card.cost == -1) {
                    flash();
                    addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractMonster q = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                            this.addToTop(new DamageAction(q, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AttackEffect.NONE));
                            addToTop(new VFXAction(new ClashEffect(q.hb.cX, q.hb.cY), 0.1f));
                        }
                    });
                }
            }

            @Override
            public void updateDescription() {
                description = "Whenever you play a cost X card, deal #b" + amount + " damage to a random enemy.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}