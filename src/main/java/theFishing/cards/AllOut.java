package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class AllOut extends AbstractFishingCard {
    public final static String ID = makeID("AllOut");
    // intellij stuff power, self, uncommon, , , , , 9, 3

    public AllOut() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 9;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("All Out", AbstractPower.PowerType.BUFF, false, p, magicNumber) {

            @Override
            public void atEndOfTurn(boolean isPlayer) {
                int x = this.amount;
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        if (AbstractDungeon.player.hand.size() == 0) {
                            flash();
                            att(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(x, true), DamageInfo.DamageType.THORNS, AttackEffect.FIRE));
                            att(new VFXAction(p, new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));
                            att(new SFXAction("ATTACK_DEFECT_BEAM"));
                        }
                    }
                });
            }

            @Override
            public void updateDescription() {
                description = "At the end of your turn, if your hand is empty, deal #b" + amount + " damage to ALL enemies.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}